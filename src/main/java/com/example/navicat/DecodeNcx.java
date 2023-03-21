package com.example.navicat;

import com.example.navicat.enums.VersionEnum;
import com.example.navicat.factory.NavicatCipherFactory;
import jodd.util.StringUtil;

/**
 * 功能描述
 * DecodeNcx 解密navicat导出的密码
 *
 * <p>
 * navicat 11采用BF(blowfish)-ECB方式，对应mode为ECB
 * navicat 12以上采用AES-128-CBC方式，对应mode为CBC
 *
 * @author Huang Kaihang
 * @version 1.0.0
 * @since 2023-03-21
 */
public class DecodeNcx {
    private static String mode;

    public DecodeNcx(String mode) {
        DecodeNcx.mode = mode;
    }

    /**
     * 根据mode进行解密
     *
     * @param str 密文
     * @return String
     */
    public String decode(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        NavicatChiper chiper = NavicatCipherFactory.get(mode);
        return chiper.decryptString(str);
    }

    public static void main(String[] args) {
        DecodeNcx decodeNcx = new DecodeNcx(VersionEnum.navicat12more.name());
        System.out.println(decodeNcx.decode("9331C9F33503BEBD70A436868A48B934"));
    }
}
