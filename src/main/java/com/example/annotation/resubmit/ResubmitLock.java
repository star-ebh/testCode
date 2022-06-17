package com.example.annotation.resubmit;


import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hkh
 * @version 1.0.0
 * @Description ResubmitLock
 * @createTime 2022年03月11日 09:47:00
 */
@Slf4j
public final class ResubmitLock {

    private static final Map<String, Object> LOCK_CACHE = new ConcurrentHashMap<>(200);
    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.DiscardPolicy());
    private static final TimedCache<String, Object> TIMED_CACHE = CacheUtil.newTimedCache(10 * 1000);
    private static final int BAN_NUMBER = 3;

    private ResubmitLock() {
    }

    /**
     * 记录请求ip
     *
     * @param request 请求对象
     */
    public synchronized void recordIp(HttpServletRequest request) {
        Object count = TIMED_CACHE.get(request.getRemoteAddr());
        TIMED_CACHE.put(request.getRemoteAddr(), count == null ? 1 : Convert.toInt(count) + 1);
    }

    /**
     * 是否重复判断
     *
     * @param request 请求对象
     * @return boolean
     */
    public boolean ipJudge(HttpServletRequest request) {
        Object count = TIMED_CACHE.get(request.getRemoteAddr());
        return count != null && Convert.toInt(count) >= BAN_NUMBER;
    }

    /**
     * 静态内部类
     */
    private static class SingletonInstance {
        private static final ResubmitLock INSTANCE = new ResubmitLock();
    }

    /**
     * 获取单例对象
     * @return ResubmitLock
     */
    public static ResubmitLock getInstance() {
        return SingletonInstance.INSTANCE;
    }

    /**
     * 计算key
     *
     * @param param 参数
     * @return key
     */
    public static String handleKey(String param) {
        return DigestUtils.md5Hex(param == null ? "" : param);
    }

    /**
     * 加锁 putIfAbsent 是原子操作保证线程安全
     *
     * @param key       对应的key
     * @param lockClass class
     * @return boolean
     */
    public boolean lock(final String key, Object lockClass) {
        return Objects.isNull(LOCK_CACHE.putIfAbsent(key, lockClass));
    }

    /**
     * 延时释放锁 用以控制短时间内的重复提交
     *
     * @param lock         是否需要解锁
     * @param key          对应的key
     * @param delaySeconds 延时时间
     */
    public void unLock(final boolean lock, final String key, final int delaySeconds) {
        if (lock) {
            EXECUTOR.schedule(() -> {
                LOCK_CACHE.remove(key);
            }, delaySeconds, TimeUnit.SECONDS);
        }
    }

}
