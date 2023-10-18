package com.example.word;

import cn.hutool.core.date.DateUtil;
import com.deepoove.poi.XWPFTemplate;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TemplateWordToPdf {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();

        // 添加键值对
        hashMap.put("Key", "Value");
        hashMap.put("KEY", "Value");
        hashMap.put("KeY", "Value");
        System.out.println(hashMap.get("key")); // 输出：Value
        System.out.println(hashMap.get("KEY")); // 输出：Value
        System.out.println(hashMap.get("KeY")); // 输出：Value
//        String test = "<p>该流程主要适用于文件（包括但不限于体系文件、内控文件等）审批及受控归档</p>";
//        System.out.println(hasText(test));

    }

    public static boolean hasText(String html) {
        // 替换HTML字符串中的<>符号
        html = html.replace("<", "").replace(">", "");
        // 去除HTML字符串中的空格
        html = html.trim();
        // 使用正则表达式来匹配HTML字符串中的文本
        Pattern pattern = Pattern.compile(">[\\s]*<");
        Matcher matcher = pattern.matcher(html);
        return matcher.find();
    }
}
