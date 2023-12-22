package com.example.file;

import cn.hutool.core.convert.Convert;

import java.math.BigDecimal;

public class Test7809 {
    public static void main(String[] args) {
        System.out.println(new BigDecimal(0).compareTo(BigDecimal.ZERO) < 1);
        System.out.println(new BigDecimal(0).compareTo(BigDecimal.ZERO));
        System.out.println(Convert.toBigDecimal("1").compareTo(BigDecimal.ZERO)<1);
    }
}
