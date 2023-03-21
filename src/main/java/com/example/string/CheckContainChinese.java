package com.example.string;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.NumberUtil;
import com.google.common.base.Strings;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS;
import static java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS;
import static java.lang.Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;

/**
 * 校验是否包含中文
 *
 * @author Huang Kaihang
 * @version 1.0.0
 * @since 2023-02-09
 */
public class CheckContainChinese {
    public static void main(String[] args) {
        //String containChinese = "20610-0007-00(禁用此码)";
        //
        //
        //boolean b = containChinese.length() >= 10 && !checkStringContainChinese(containChinese) && !containChinese.contains("old")
        //        && !containChinese.contains("OLD");
        //System.out.println("containChinese 是否不包含中文 :" + b);
        //System.out.println("containChinese 是否包含中文 :" + checkStringContainChinese("-"));
        //System.out.println(new BigDecimal(14774577.09).stripTrailingZeros().toPlainString());
        //System.out.println(new BigDecimal(14774577.09).stripTrailingZeros().toPlainString());

        //System.out.println(14774577.09 + "人民币");
        //BigDecimal bigDecimal2 = new BigDecimal(1);
        //
        //
        //
        //System.out.println(bigDecimal1.compareTo(BigDecimal.valueOf(1)) != 0);
        //
        //System.out.println(NumberUtil.isGreaterOrEqual(bigDecimal1, bigDecimal2));
        //System.out.println(NumberUtil.equals(bigDecimal1, bigDecimal2));
        //BigDecimal bigDecimal1 = new BigDecimal(1.000000);
        //Map<String, Object> stringObjectHashMap = new HashMap<>();
        //stringObjectHashMap.put("123","123123");
        //Object o = stringObjectHashMap.get(null);
        //System.out.println(o);
        DateTime effectiveDate = DateUtil.parse("2023-03-22", "yyyy-MM-dd");
        DateTime currentDate =  DateUtil.parse(DateUtil.today(), "yyyy-MM-dd");
        boolean b = (effectiveDate.before(currentDate) || effectiveDate.equals(currentDate)) && (effectiveDate.after(currentDate) || effectiveDate.equals(currentDate));
        Console.log("早于=>{}", effectiveDate.before(currentDate) );
        Console.log("晚于=>{}", effectiveDate.after(currentDate) );
        Console.log("等于=>{}", effectiveDate.equals(currentDate));

    }

    private static boolean checkStringContainChinese(String checkStr) {
        if (!Strings.isNullOrEmpty(checkStr)) {
            char[] checkChars = checkStr.toCharArray();
            for (int i = 0; i < checkChars.length; i++) {
                char checkChar = checkChars[i];
                if (checkCharContainChinese(checkChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCharContainChinese(char checkChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(checkChar);
        if (CJK_UNIFIED_IDEOGRAPHS == ub || CJK_COMPATIBILITY_IDEOGRAPHS == ub || CJK_COMPATIBILITY_FORMS == ub ||
                CJK_RADICALS_SUPPLEMENT == ub || CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A == ub || CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B == ub) {
            return true;
        }
        return false;
    }

}
