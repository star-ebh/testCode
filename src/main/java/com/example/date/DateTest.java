package com.example.date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Sets;
import org.apache.commons.lang.StringEscapeUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

public class DateTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.with(ChronoField.DAY_OF_WEEK, 4)
                .with(ChronoField.HOUR_OF_DAY, 19)
                .with(ChronoField.MINUTE_OF_HOUR, 30);
        System.out.println(DateUtil.format(start, DatePattern.CHINESE_DATE_TIME_PATTERN));
        LocalDateTime end = now.with(ChronoField.DAY_OF_WEEK, 5)
                .with(ChronoField.HOUR_OF_DAY, 7)
                .with(ChronoField.MINUTE_OF_HOUR, 30);
        System.out.println(DateUtil.format(end, DatePattern.CHINESE_DATE_TIME_PATTERN));

        if (now.isAfter(start) && now.isBefore(end)) {
            System.out.println("当前时间在周四晚上19:30到周五早上7:30的时间范围内");
        } else {
            System.out.println("当前时间不在周四晚上19:30到周五早上7:30的时间范围内");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("field_name", "Rsxzczx12333");
        jsonObject.put("value", JSON.toJSONString(Collections.singletonList("12333")));
        System.out.println(jsonObject.toString());
        System.out.println(DateUtil.offsetMonth(DateUtil.parse("2023-10-23"), 6 - 1)
                .toDateStr());
        System.out.println(String.format(Locale.ROOT,"\\\"%s\\\"","41%"));
    }
}
