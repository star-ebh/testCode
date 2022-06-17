package com.example.annotation.resubmit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author hkh
 * @version 1.0.0
 * @Description ResubmitDataAspect
 * @createTime 2022年03月11日 10:47:00
 */
@Slf4j
//@Aspect
//@Component
public class ResubmitDataAspect {

    private final static Object PRESENT = new Object();

    @Around("@annotation(com.robosense.datacenter.utils.resubmit.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (ResubmitLock.getInstance().ipJudge(request)) {
            return "禁止重复提交";
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取注解信息
        Resubmit annotation = method.getAnnotation(Resubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();
        String key = "";
        // 获取第一个参数
        Object firstParam = pointArgs[0];
        if (firstParam != null) {
            // 使用content_MD5的加密
            key = ResubmitLock.handleKey(firstParam.toString());
        }
        // 执行锁
        boolean lock = false;
        try {
            // 设置解锁key
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                // 放行
                return joinPoint.proceed();
            }
            ResubmitLock.getInstance().recordIp(request);
            return "请勿频繁操作";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("加锁异常了->{}", e.getMessage());
            return "加锁异常了";
        } finally {
            //设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}