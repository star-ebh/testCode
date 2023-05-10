package com.example.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import java.util.Locale;

/**
 * @author hkh
 */

public class TestController {
    private static final String TEMPORARY_PREFIX = "LS-";

    public static void main(String[] args) {
        Integer serialNumber = getSerialNumber("LS1-80400-0051-00");
        System.out.println("1=>" + testCode1("LS1-80400-0051-00", "80401"));
        System.out.println("2=>" + testCode1("LS1-80400-0051-00", "80400"));
        System.out.println("3=>" + testCode1("80400-0051-00", "80401"));
        System.out.println("4=>" + testCode1("80400-0051-00", "80400"));
        System.out.println("5=>" + testCode1("LS1-80400-0051-00", "803"));
        System.out.println("6=>" + testCode1("LS1-80400-0051-00", "804"));
        System.out.println("7=>" + testCode1("80400-0051-00", "803"));
        System.out.println("8=>" + testCode1("80400-0051-00", "804"));
        System.out.println("9=>" + testCode1("LS1-80400-0051-00", "7"));
        System.out.println("10=>" + testCode1("LS1-80400-0051-00", "8"));
        System.out.println("11=>" + testCode1("80400-0051-00", "6"));
        System.out.println("12=>" + testCode1("80400-0051-00", "8"));

    }

    public static Integer getSerialNumber(String code) {
        // 移除临时编码标记
        code = code.replaceAll("LS\\d*-", "");
        // 截取流水号
        String[] parts = StringUtils.split(code, '-');
        if (parts.length != 3) {
            throw new IllegalArgumentException("输入参数格式有误");
        }
        return Integer.parseInt(parts[2]);
    }

    public static String testCode1(String materialCode, String beginCode) {
        try {
            if (StringUtils.isAnyBlank(materialCode, beginCode)) {
                return String.valueOf(false);
            }
            // 判断编码位数
            int beginCodeLength = beginCode.length();
            // 物料编码去除临时编码标识
            String afterCutCode = materialCode.replaceAll("LS\\d*-", "");
            // 截取编码和判断编码位数一致
            String judgmentCode = afterCutCode.substring(0, beginCodeLength);
            return String.valueOf(StringUtils.equals(judgmentCode, beginCode)).toUpperCase(Locale.ROOT);
        } catch (Exception e) {
            return String.valueOf(false);
        }
    }
}
