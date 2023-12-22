package com.example.feishu;

import cn.hutool.core.text.StrSplitter;
import com.example.json.CommonConstant;
import com.lark.oapi.Client;
import com.lark.oapi.core.utils.Jsons;
import com.lark.oapi.service.auth.v3.model.CreateAppAccessTokenReq;
import com.lark.oapi.service.auth.v3.model.CreateAppAccessTokenReqBody;
import com.lark.oapi.service.auth.v3.model.CreateAppAccessTokenResp;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
public class Test2 {
    public static void main(String[] args) throws Exception {
        String startMac = "40-2C-76-85-00-00";
        long countMac = countMac(startMac, "40-2C-76-8F-FF-FF");
        System.out.println("总数=>" + countMac);
        System.out.println("总数=>" + countMac("40-2C-76-80-00-00", "40-2C-76-8F-FF-FF"));
//        long macAddressLong = turnMacToLong(startMac);

//        for (long index = 0L; index < countMac; index++) {
//            // 计算当前MAC地址
//            long indexMacAddress = macAddressLong + index;
//            // 转字符串MAC地址
//            String[] macAddressArray =
//                    StrSplitter.splitByLength(Long.toHexString(indexMacAddress), CommonConstant.QUANTITY_TWO);
//            // 转大写 ff -> FF
//            String macAddressStr = String.join("-", macAddressArray).toUpperCase(Locale.ROOT);
//            System.out.println("macAddressStr=>" + macAddressStr);
//        }
    }
//    public static void main(String[] args) throws Exception {
//        // 分隔符
//        String delimiter = "-";
//        // 开始MAC和结束MAC
//        String startMac = String.format(Locale.ROOT, "%s0%s00%s00", "40-2C-76-8", delimiter, delimiter);
//        String endMac = String.format(Locale.ROOT, "%sF%sFF%sFF", "40-2C-76-8", delimiter, delimiter);
//        // 40-2C-76-80-00-00
//        // 40-2C-76-8F-FF-FF
//
//        // 40-2C-76-80-00-00 40-2C-76-81-FF-FF
//        // 40-2C-76-81-00-FF
//        // 40-2C-76-82-00-FF
//        // 40-2C-76-83-00-FF
//        // 40-2C-76-84-00-FF
//        // 40-2C-76-85-00-FF
//        // 40-2C-76-...
//        // 40-2C-76-8F-FF-FF
//        long l3 = countMac(startMac, endMac);
//        long l4 = countMac("40-2C-76-80-00-00", "40-2C-76-80-FF-FF");
//
//        log.info("l3总数-{}", l3);
//        log.info("l4总数-{}", l4);
//
//        long i0 = Long.parseLong("0", 16);
//        String s1 = Long.toHexString(0L).toUpperCase(Locale.ROOT);
//        String s2 = Long.toHexString(15L).toUpperCase(Locale.ROOT);
//        long i9 = Long.parseLong("9", 16);
//        long ia = Long.parseLong("A", 16);
//        long ib = Long.parseLong("B", 16);
//        long ic = Long.parseLong("C", 16);
//        long id = Long.parseLong("D", 16);
//        long ie = Long.parseLong("E", 16);
//        long ifa = Long.parseLong("F", 16);
//
//        long l1 = turnMacToLong(startMac);
//        String s = Long.toHexString(l1);
//        long l2 = turnMacToLong(endMac);
//        long l = l2 - l1 + 1;
//        log.info("总数-{}", l);
//    }

    public static long countMac(String macStart, String macEnd) {
        String hexStart = macStart.replace("-", "");
        String hexEnd = macEnd.replace("-", "");
        long start = Long.parseLong(hexStart, 16);
        long end = Long.parseLong(hexEnd, 16);
        return end - start + 1;
    }

    /**
     * 字符串MAC地址转数字
     *
     * @param macAddress mac地址
     * @return long
     * @Author Huang kaihang
     * @Description 字符串MAC地址转数字
     * @Date 22:53 2022/8/29
     **/
    public static long turnMacToLong(String macAddress) {
        String hex = macAddress.replace("-", "");
        return Long.parseLong(hex, 16);
    }

}
