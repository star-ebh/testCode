package com.example.common.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 日期转换类 转换一个 java.util.Date 对象到一个字符串以及 一个字符串到一个 java.util.Date 对象.
 */
public class DateUtil {
    public static final long SECOND = 1000;

    public static final long MINUTE = SECOND * 60;

    public static final long HOUR = MINUTE * 60;

    public static final long DAY = HOUR * 24;

    public static final long WEEK = DAY * 7;

    public static final long MONTH = DAY * 30;

    public static final long YEAR = DAY * 365;

    public static final String TYPE_DATE = "date";

    public static final String TYPE_TIME = "time";

    public static final String TYPE_DATETIME = "datetime";

    public static final String TYPE_TIME_SEC = "time.sec";

    private static final ThreadLocal<Long> LASTTIME = new ThreadLocal<Long>();

    /**
     * 模式:yyyy-MM-dd HH:mm
     */
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm";

    /**
     * 模式:yyyy-MM-dd
     */
    public static final String PATTERN_DATE = "yyyy-MM-dd";

    private static final String DEFAULT_PATTERN = PATTERN_DATETIME;

    public static final String[] TYPE_ALL = {TYPE_DATE, TYPE_DATETIME,
            TYPE_TIME};



    /**
     * 将字符串转换为Date类型
     *
     * @param strDate
     * @param pattern 格式
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate, String pattern) {

        if (StrUtil.isEmpty(strDate) || "on".equals(strDate))
            return null;
        if (StrUtil.isEmpty(pattern))
            pattern = DEFAULT_PATTERN;
        // 在多语言的打开并且打开快捷时获取的msg的信息存在[],通过正则去除[] eg:[HH:mm]->HH:mm 该问题详见ResourceUtil.getStringValue(String key, String bundle, Locale locale) 返回值 by 王进府
        String localPattern = pattern.replaceAll("\\]|\\[", "");
        SimpleDateFormat df = new SimpleDateFormat(localPattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字符串转换为Date类型,字段识别日期格式
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) {
        if (StrUtil.isEmpty(strDate))
            return null;
        if (Pattern.matches("^\\d+$", strDate)) {
            //如果全是数字，则表示它是long类型的日期数据
            return new Date(Long.valueOf(strDate));
        }
        return convertStringToDate(strDate, getDateFormat(strDate));
    }

    /**
     * 常规自动日期格式识别
     *
     * @param str 时间字符串
     * @return Date
     * @author dc
     */
    public static String getDateFormat(String str) {
        boolean year = false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if (pattern.matcher(str.substring(0, 4)).matches()) {
            year = true;
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        if (!year) {
            if (str.contains("月") || str.contains("-") || str.contains("/")) {
                if (Character.isDigit(str.charAt(0))) {
                    index = 1;
                }
            } else {
                index = 3;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (Character.isDigit(chr)) {
                if (index == 0) {
                    sb.append("y");
                }
                if (index == 1) {
                    sb.append("M");
                }
                if (index == 2) {
                    sb.append("d");
                }
                if (index == 3) {
                    sb.append("H");
                }
                if (index == 4) {
                    sb.append("m");
                }
                if (index == 5) {
                    sb.append("s");
                }
                if (index == 6) {
                    sb.append("S");
                }
            } else {
                if (i > 0) {
                    char lastChar = str.charAt(i - 1);
                    if (Character.isDigit(lastChar)) {
                        index++;
                    }
                }
                sb.append(chr);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String  strDate= "2022/10/28 0:00";
        String dateFormat = getDateFormat(strDate);
        Console.log("{}",dateFormat);
        Date date = convertStringToDate(strDate, dateFormat);
        Console.log("{}",date);
    }
}
