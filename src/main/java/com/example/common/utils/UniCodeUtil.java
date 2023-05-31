package com.example.common.utils;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HtmlUtil;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniCodeUtil {
    /*
     * unicode编码
     */
    public static String stringToUnicode(String str) {
        char[] utfBytes = str.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }

    /**
     * Unicode解码方式2
     */
    private static String unicodeToString2(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            // 本行为核心代码，处理当前的unicode后4位变为16进制，在转换为对应的char中文字符
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * Ascii转换为字符串
     *
     * @param value
     * @return
     */
    public static String asciiTransformString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static void main(String[] args) {
        String str = "FNumber NOT IN (&#39;100.01&#39;,&#39;100.02&#39;,&#39;102&#39;,&#39;104&#39;,&#39;105&#39;) AND FDocumentStatus  =&#39;C&#39;";
        String str2 = "FNumber NOT IN ('100.01','100.02','102','104','105') AND FDocumentStatus  ='C'";
        System.out.println(unicodeToString2(str));
        System.out.println(UnicodeUtil.toString(str));
        System.out.println(StringEscapeUtils.unescapeHtml(str));
        System.out.println(StringEscapeUtils.unescapeHtml(str2));
    }
}
