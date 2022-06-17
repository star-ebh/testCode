//package com.example;
//
//import cn.hutool.core.thread.ThreadUtil;
//import cn.hutool.core.util.StrUtil;
//import com.example.tcp.client.NettyClientService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//@Slf4j
//@SpringBootTest
//class DemoApplicationTests {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Autowired
//    private NettyClientService clientService;
//
//    @Test
//    void contextLoads() {
//        String result = clientService.sendSyncMsg("222", "111", "111");
//        log.info("{}",result);
//    }
//
//    @Test
//    void redissonTest01() {
//        lockMethod();
//    }
//
//    private void lockMethod() {
//        RLock lock = redissonClient.getLock("testNumber");
//        try{
//            lock.lock();
//            System.out.println("休眠");
//            ThreadUtil.sleep(60000);
//            System.out.println("结束");
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//}
