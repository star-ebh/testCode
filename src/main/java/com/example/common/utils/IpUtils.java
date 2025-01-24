package com.example.common.utils;

/**
 * @author hkh
 * @version 1.0.0
 * @Description IP工具类
 * @createTime 2022年03月03日 15:21:00
 */
public class IpUtils {
    public static void main(String[] args) {
        System.out.println(ipToLong("192.168.24.81"));
        System.out.println(longToIp(3232241745L));

    }

    /**
     * 把字符串IP转换成long
     *
     * @param ipStr 字符串IP
     * @return IP对应的long值
     */
    public static long ipToLong(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16)
                + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     *
     * @param ipLong IP的long值
     * @return long值对应的字符串
     */
    public static String longToIp(long ipLong) {
        return (ipLong >>> 24) + "." +
                ((ipLong >>> 16) & 0xFF) + "." +
                ((ipLong >>> 8) & 0xFF) + "." +
                (ipLong & 0xFF);
    }

    /**
     * 是否为同一网段
     *
     * @param ip            IP
     * @param numberSection 编号部分
     * @return boolean
     */
    public static boolean isEqualsSection(String ip, long numberSection) {
        long takeMold = ipToLong(ip) % 256;
        return takeMold == numberSection;
    }

}
