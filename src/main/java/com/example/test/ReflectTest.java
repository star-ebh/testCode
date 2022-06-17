package com.example.test;

import cn.hutool.core.lang.Console;

/**
 * @author hkh
 * @version 1.0.0
 * @ClassName ReflectTest
 * @Description TODO
 * @createTime 2022/6/17 0:06
 */
public class ReflectTest {

    private static void getId(String a, String b) {
        Console.log("私有静态方法被调用,a->{},b->{}", a, b);
    }
}
