//package com.example.redisson;
//
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.config.SingleServerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author hkh
// * @version 1.0.0
// * @Description RedissonConfig
// * @createTime 2022年03月11日 16:21:00
// */
//@Configuration
//public class RedissonConfig {
//
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
////        config.useClusterServers()
////                .setScanInterval(2000)
////                .addNodeAddress("redis://127.0.0.1:6379");
//        SingleServerConfig singleServerConfig = config.useSingleServer()
//                .setAddress("redis://127.0.0.1:6379");
//        return Redisson.create(config);
//    }
//
//}