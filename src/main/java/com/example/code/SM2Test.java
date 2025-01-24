package com.example.code;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.robosense.core.utils.SM2Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SM2Test {
//    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("abc", "123");
//        String publicKey = "0474A35F34C05C378C46F663D027E5F8AE097A756BE1AE95C1CA7A8104D3BCC29D9388F6140DCC3F63FF257B25B726C75D13919A792EB2EE4D0F85DD98403B7AF8";
//        String priKey = "3FFE7F96E879909109C857DE7D8CA00B21C10DC56F0651A47C9DF3E9B7475A5D";
//        byte[] bytes = SM2Util.encrypt(publicKey, JSON.toJSONString(jsonObject).getBytes(StandardCharsets.UTF_8));
//        Console.log("密文1=>" + Base64.encode(bytes));
//        Console.log("明文1=>" +  new String(SM2Util.decrypt(priKey, bytes)));
//        byte[] decode1 = Base64.decode("BFzEbymZR1A0UNB4pZflu+5inHQAkSz6T0D/+RdhV2NImAot1jrazvYqR89hS9sBlV5hE+vzg9649vgNFNL8609pHbkhxNVVfe6goTGHPvQNFlAX1gerxfAY5jV2INod6UDn3WWW4A==");
//        Console.log("明文2=>" + new String(SM2Util.decrypt(priKey, decode1)));
//    }
    public static void main(String[] args) {
        String string = "[{\"invType\":\"10\",\"note\":\"\",\"usedReimAmount\":0,\"fileName\":\"深圳市速腾聚创科技有限公司\",\"checkMsg\":\"查验成功\",\"usableReimAmount\":696,\"invCodeNo\":\"044032200411_34569863\",\"buyTaxNo\":\"91440300312056206Y\",\"lockingReimAmount\":0,\"matchStatus\":2,\"totalMoneyUp\":\"陆佰玖拾陆元整\",\"sellTaxNo\":\"91440300MA5EXEHG6X\",\"checked\":1,\"machineNo\":\"661811208840\",\"details\":[{\"amountWithoutTax\":656.6,\"standard\":\"\",\"number\":1,\"unit\":\"\",\"rate\":\"6\",\"withoutTaxPrice\":\"656.603773580000\",\"rowIndex\":0,\"taxAmount\":39.4,\"goodsName\":\"*餐饮服务*餐费\"}],\"fileUrl\":\"https://vat.robosense.cn/prod-api/api/file/view/1730491877582573568/Q0ZDMDkzRjY2NUIwNEU4Nzk1RTg3NkZGMUFDQjJEMTM=.pdf\",\"sellAddrTel\":\"\\\"深圳市南山区粤海街道海德二道258号茂业时代广场+8层海底捞火锅\\\"+0755-23008767\",\"state\":0,\"reimStatus\":0,\"totalAmountWithoutTax\":656.6,\"sellName\":\"深圳市海底捞餐饮有限责任公司第十九分公司\",\"serialNumber\":\"1764837677674401792\",\"taxCredits\":\"0\",\"buyAddrTel\":\"\",\"buyName\":\"深圳市速腾聚创科技有限公司\",\"invNo\":\"34569863\",\"isAlreadySelect\":0,\"checkCode\":\"45165767130473342708\",\"buyBankAccount\":\"\",\"totalAmount\":696,\"createTime\":\"2024-03-05+10:17:46.1640\",\"sellBankAccount\":\"中国民生银行股份有限公司深圳桃园支行+610603002\",\"invCode\":\"044032200411\",\"totalTaxAmount\":39.4,\"invDate\":\"2024-03-01\",\"fullReimbursement\":\"1\",\"reimbursementAmount\":696,\"_X_ROW_KEY\":\"row_72\"}]";
        JSONArray array = JSON.parseArray(string);
    }
}
