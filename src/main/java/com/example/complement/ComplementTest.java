//package com.example.complement;
//
//import cn.hutool.core.date.DateTime;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Console;
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Maps;
//import com.google.gson.Gson;
//import org.apache.commons.compress.utils.Lists;
//import org.apache.commons.lang3.StringUtils;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 补位测试
// *
// * @author Huang Kaihang
// * @version 1.0.0
// * @since 2022-11-30
// */
//public class ComplementTest {
//    /**
//     * public static void main(String[] args) {
//     * <p>
//     * String s2 = JSON.toJSONString("[{\"TestStatus\":\"P\",\"ptx0\":\"63.867\",\"SN\":\"22071100BG00729\",\"black_out_diff\":\"-2\",\"D0\":\"142.465\",\"sg1\":\"227.450\",\"module_tempreature\":\"19.500\"}]");
//     * Console.log(s2);
//     * String test = "[{Angle1=3132, Torque1=0.1401, Angle2=3127, Torque2=0.1401, Angle3=2931, Torque3=0.1401, Angle4=2977, " +
//     * "Torque4=0.1402, Angle5=1302, Torque5=0.1402, Angle6=1170, Torque6=0.1401, Screwdriver=003, " +
//     * "SN=2208070000038L-0215, CarrierSN=ZP-M1P0090, Module2SN=, Module4SN=, TestStatus=P, " +
//     * "SubSNList=[{SubSN=M1PG22072900042, TestStatus=P, Part=MemsSN, NO=1}]}]";
//     * String test2 = "[{Angle1:3132,Torque1:0.1401,Angle2:3127,Torque2:0.1401,Angle3:2931,Torque3:0.1401,Angle4:2977," +
//     * "Torque4:0.1402,Angle5:1302,Torque5:0.1402,Angle6:1170,Torque6:0.1401,Screwdriver:003,SN:2208070000038L-0215," +
//     * "CarrierSN:ZP-M1P0090,Module2SN:,Module4SN:,TestStatus:P,SubSNList:[{SubSN:M1PG22072900042,TestStatus:P,Part:MemsSN,NO:1}]}]";
//     * String s = test.replaceAll("=", ":");
//     * String s1 = s.replaceAll(" ", "");
//     * StringBuilder builder = new StringBuilder(s1);
//     * StringBuilder builder2 = new StringBuilder();
//     * <p>
//     * for (int i = 0; i < builder.length(); i++) {
//     * char ch = builder.charAt(i);
//     * if (ch == '{' && builder.charAt(i - 1) != '[') {
//     * builder2.append(ch).append("\"");
//     * } else if (ch == '{' && builder.charAt(i - 1) == '[') {
//     * builder2.append(ch).append("\"");
//     * } else if (ch == '}' && builder.charAt(i + 1) != ']') {
//     * builder2.append("\"").append(ch);
//     * } else if (ch == '}' && builder.charAt(i + 1) == ']' && builder.charAt(i - 1) != ']') {
//     * builder2.append("\"").append(ch);
//     * } else if (ch == ':' && builder.charAt(i + 1) == '[') {
//     * builder2.append("\"").append(ch);
//     * } else if (ch == ':' && builder.charAt(i + 1) != '[') {
//     * builder2.append("\"").append(ch).append("\"");
//     * } else if (ch == ',') {
//     * builder2.append("\"").append(ch).append("\"");
//     * } else if (i + 1 > builder.length() && builder.charAt(i + 1) == '}') {
//     * builder2.append(ch).append("\"");
//     * } else {
//     * builder2.append(ch);
//     * }
//     * }
//     * System.out.println(builder2);
//     * }
//     **/
//
//
//    public static void main(String[] args) {
//
//        String replaceAll = "12312.213".replace("(\\d)(?=(\\d{3})+\\.)",  "$1,");
//        System.out.println("replaceAll->" + replaceAll);
//
//    }
//
//
//}
//
//class Data {
//    private Map<String, Object> a;
//
//    public Data() {
//        Map<String, Object> a = Maps.newHashMap();
//        a.put("测试1", "a");
//        Map<String, Object> b = Maps.newHashMap();
//        b.put("测试key", "123");
//        a.put("bMap", b);
//        Console.log("类内-{}", a);
//        b.put("测试key", "456");
//        Console.log("类内-{}", a);
//        this.a = a;
//    }
//
//
//    public Map<String, Object> getModelData() {
//        return this.a;
//    }
//
//    public void show() {
//        Console.log("show-{}", a);
//    }
//}
