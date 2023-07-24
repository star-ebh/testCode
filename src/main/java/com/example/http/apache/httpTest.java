package com.example.http.apache;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.UnicodeUtil;

import java.io.InputStream;

public class httpTest {
    public static void main(String[] args) {
//        String date = "2019-05-30T00:00:00";
//        DateTime parse = DateUtil.parse(date);
//        System.out.println(parse.toDateStr());
        String filter = "test != 123";
        String field = filter.substring(0, filter.indexOf("!="));
        String symbol = filter.substring(filter.indexOf("!="), filter.lastIndexOf("!=") + 2);
        String val = filter.substring( filter.lastIndexOf("!=") + 2);
        System.out.println(field.trim());
        System.out.println(symbol.trim());
        System.out.println(val.trim());
    }
}
