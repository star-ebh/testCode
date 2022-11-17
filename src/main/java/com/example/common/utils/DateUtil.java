package com.example.common.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.compress.utils.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    //public static void main(String[] args) {
        //ThreadUtil.concurrencyTest(1000, () -> {
        //    List<Object> objects = Lists.newArrayList();
        //for (int j = 0; j < 100; j++) {
        //    for (int i = 0; i < 1000; i++) {
        //        JSONObject jsonObject = new JSONObject();
        //        jsonObject.put("internalSn", "z850frwzxcq2" + RandomUtil.randomString(3) + i);
        //        jsonObject.put("typeCode", "1");
        //        jsonObject.put("productCode", "0214");
        //        jsonObject.put("materialCode", "80-800-800-800");
        //        JSONObject otherParameters = new JSONObject();
        //        otherParameters.put("Customer_Material", "测试a" + i);
        //        otherParameters.put("Supplier_NO", "测试b" + i);
        //        otherParameters.put("Plarform_NO", "测试c" + i);
        //        otherParameters.put("Part_NO", "测试d" + i);
        //        jsonObject.put("otherParameters", otherParameters);
        //        objects.add(jsonObject);
        //    }
        //    String result2 = HttpRequest.post("http://10.40.66.19:7011/dataWarehouse/snMacInfo/add")
        //            .body(JSON.toJSONString(objects))
        //            .timeout(200000000)
        //            .execute().body();
        //    Console.log(result2);
        //}
        //});
    //}

    public static void main(String[] args) {
        List list = testCode();
        Console.log("{}",list);
    }

    private static List testCode() {
        List sList1 = new ArrayList();
        sList1.add("10101");sList1.add("10102");sList1.add("10103");sList1.add("10104");sList1.add("10105");sList1.add("10106");
        sList1.add("10107");sList1.add("10108");sList1.add("10109");sList1.add("10110");sList1.add("10111");sList1.add("10112");
        sList1.add("10113");sList1.add("10114");sList1.add("10115");sList1.add("10116");sList1.add("10117");sList1.add("10118");
        sList1.add("10119");sList1.add("10120");sList1.add("10121");sList1.add("10122");sList1.add("10123");sList1.add("10124");
        sList1.add("10125");sList1.add("10126");sList1.add("10127");sList1.add("10128");sList1.add("10201");sList1.add("10202");
        sList1.add("10203");sList1.add("10204");sList1.add("10205");sList1.add("10206");sList1.add("10301");sList1.add("10302");
        sList1.add("10303");sList1.add("10304");sList1.add("10305");sList1.add("10306");sList1.add("10401");sList1.add("10402");
        sList1.add("10403");sList1.add("10404");sList1.add("10405");sList1.add("10406");sList1.add("10407");sList1.add("10408");
        sList1.add("10409");sList1.add("10410");sList1.add("10501");sList1.add("10502");sList1.add("10503");sList1.add("10504");
        sList1.add("10505");sList1.add("10506");sList1.add("10507");sList1.add("10601");sList1.add("10602");sList1.add("10603");
        sList1.add("10604");sList1.add("10605");sList1.add("10606");sList1.add("10701");sList1.add("10702");sList1.add("10703");
        sList1.add("10704");sList1.add("10801");sList1.add("10802");sList1.add("10803");sList1.add("10804");sList1.add("10805");
        sList1.add("10806");sList1.add("10807");sList1.add("10808");sList1.add("10809");sList1.add("10901");sList1.add("10902");
        sList1.add("10903");sList1.add("10904");sList1.add("11001");sList1.add("11002");sList1.add("11003");sList1.add("11004");
        sList1.add("11101");sList1.add("11201");sList1.add("11202");sList1.add("11203");sList1.add("11204");sList1.add("11205");
        sList1.add("11206");sList1.add("11207");sList1.add("11208");sList1.add("11209");sList1.add("11210");sList1.add("11211");
        sList1.add("11212");sList1.add("11213");sList1.add("11214");sList1.add("11401");sList1.add("11402");sList1.add("11403");
        sList1.add("11404");sList1.add("11405");sList1.add("11501");sList1.add("12601");
        List sList2 = new ArrayList();
        sList2.add("11406");sList2.add("11407");sList2.add("11408");sList2.add("11409");sList2.add("11410");sList2.add("11411");
        sList2.add("11412");sList2.add("11901");sList2.add("11902");sList2.add("11903");sList2.add("11904");sList2.add("11905");
        sList2.add("11906");sList2.add("11907");sList2.add("11908");sList2.add("11909");sList2.add("11910");sList2.add("11911");
        sList2.add("11912");sList2.add("11913");sList2.add("12001");sList2.add("12002");sList2.add("12003");sList2.add("12004");
        sList2.add("12301");sList2.add("12302");sList2.add("12303");sList2.add("20101");sList2.add("20102");sList2.add("20103");
        sList2.add("20104");sList2.add("20105");sList2.add("20106");sList2.add("20107");sList2.add("20108");sList2.add("20109");
        sList2.add("20110");sList2.add("20111");sList2.add("20201");sList2.add("20202");sList2.add("20203");sList2.add("20204");
        sList2.add("20205");sList2.add("20206");sList2.add("20207");sList2.add("20208");sList2.add("20209");sList2.add("20210");
        sList2.add("20301");sList2.add("20302");sList2.add("20303");sList2.add("20304");sList2.add("20305");sList2.add("20306");
        sList2.add("20307");sList2.add("20308");sList2.add("20309");sList2.add("20401");sList2.add("20402");sList2.add("20403");
        sList2.add("20404");sList2.add("20405");sList2.add("20406");sList2.add("20501");sList2.add("20502");sList2.add("20503");
        sList2.add("20504");sList2.add("20505");sList2.add("20506");sList2.add("20507");sList2.add("20601");sList2.add("20602");
        sList2.add("20603");sList2.add("20604");sList2.add("20605");sList2.add("20606");sList2.add("20607");sList2.add("20608");
        sList2.add("20609");sList2.add("20610");sList2.add("20611");sList2.add("20612");sList2.add("20613");sList2.add("20614");
        sList2.add("20701");sList2.add("20702");sList2.add("20801");
        List sList3 = new ArrayList();
        sList3.add("11601");sList3.add("11701");sList3.add("11702");sList3.add("11703");sList3.add("11704");sList3.add("11705");
        sList3.add("11801");sList3.add("11802");sList3.add("11803");sList3.add("12101");sList3.add("12102");sList3.add("12103");
        sList3.add("12104");sList3.add("12105");sList3.add("12106");sList3.add("12107");sList3.add("12201");sList3.add("12401");
        sList3.add("12501");sList3.add("12502");sList3.add("12503");sList3.add("12504");sList3.add("70101");sList3.add("70201");
        sList3.add("70301");sList3.add("70401");sList3.add("80100");sList3.add("80200");sList3.add("80300");sList3.add("80400");
        sList3.add("80500");sList3.add("80501");sList3.add("80502");sList3.add("80503");sList3.add("80504");sList3.add("80600");
        sList3.add("80601");sList3.add("80700");sList3.add("80800");sList3.add("80900");

        List list = new ArrayList();
        List<String> list1 = Arrays.asList("3","1","2","2","2","2","4","3");
        List<String> list2 = Arrays.asList("","","","","","","","");
        List<String> list3 = Arrays.asList("","","","","","","","");
        List<String> list4 = Arrays.asList("","","","","","","","");
        List<String> list5 = Arrays.asList("07","13","03","03","02","01","01","02");
        List<String> list6 = Arrays.asList("18","02","01","01","06","01","01","01");
        List<String> list8 = new ArrayList();
        String  type = "新增";
        if(list1 != null && "新增".equals(type)){
            for (int i = 0; i < list1.size(); i++) {
                if(StrUtil.isBlank(list1.get(i)) || StrUtil.isBlank(list5.get(i))){
                    continue;
                }
                String code = list1.get(i).toString() + list5.get(i).toString();
                String code2 = list1.get(i) + list5.get(i) + list6.get(i);
                if(sList1.contains(code2)){
                    list8.add("yaami.yang");
                }else if(sList2.contains(code2)){
                    list8.add("sqyu");
                }else if(sList3.contains(code2)){
                    list8.add("lydia.dong");
                }
            }
        }
        if(list2 != null && "升级".equals(type)){
            for (int i = 0; i < list2.size(); i++) {
                if(StrUtil.isBlank(list2.get(i))){
                    continue;
                }
                String tempCode = list2.get(i);
                String classVal1 = "";
                String classVal2 = "";
                if(tempCode.indexOf("LS") == -1 ){
                    classVal1 = tempCode.substring(0, 3);
                    classVal2 = tempCode.substring(0, 5);
                }else{
                    classVal1 = tempCode.substring(tempCode.indexOf("-") + 1, tempCode.indexOf("-") + 4);
                    classVal2 = tempCode.substring(tempCode.indexOf("-") + 1, tempCode.indexOf("-") + 6);
                }
                if(sList1.contains(classVal2)){
                    list8.add("yaami.yang");
                }else if(sList2.contains(classVal2)){
                    list8.add("sqyu");
                }else if(sList3.contains(classVal2)){
                    list8.add("lydia.dong");
                }
            }
        }
        if(list3 != null  && "勘误".equals(type)){
            for (int i = 0; i < list3.size(); i++) {
                if(StrUtil.isBlank(list3.get(i))){
                    continue;
                }
                String tempCode = list3.get(i);
                String classVal1 = "";
                String classVal2 = "";
                if(tempCode.indexOf("LS") == -1 ){
                    classVal1 = tempCode.substring(0, 3);
                    classVal2 = tempCode.substring(0, 5);
                }else{
                    classVal1 = tempCode.substring(tempCode.indexOf("-") + 1, tempCode.indexOf("-") + 4);
                    classVal2 = tempCode.substring(tempCode.indexOf("-") + 1, tempCode.indexOf("-") + 6);
                }
                if(sList1.contains(classVal2)){
                    list8.add("yaami.yang");
                }else if(sList2.contains(classVal2)){
                    list8.add("sqyu");
                }else if(sList3.contains(classVal2)){
                    list8.add("lydia.dong");
                }
            }
        }
        if(list4 != null  && "新增".equals(type)){
            for (int i = 0; i < list4.size(); i++) {
                if(StrUtil.isBlank(list4.get(i))){
                    continue;
                }
                String tempCode = list4.get(i);
                String classVal1 = "";
                String classVal2 = "";
                if(tempCode.indexOf("LS") == -1 ){
                    classVal1 = tempCode.substring(0, 3);
                    classVal2 = tempCode.substring(0, 5);
                }else{
                    classVal1 = tempCode.substring(tempCode.indexOf("-") + 1, tempCode.indexOf("-") + 4);
                    classVal2 = tempCode.substring(tempCode.indexOf("-") + 1, tempCode.indexOf("-") + 6);
                }
                if(sList1.contains(classVal2)){
                    list8.add("yaami.yang");
                }else if(sList2.contains(classVal2)){
                    list8.add("sqyu");
                }else if(sList3.contains(classVal2)){
                    list8.add("lydia.dong");
                }
            }
        }
        return list8;
    }
}
