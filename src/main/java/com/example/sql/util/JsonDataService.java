package com.example.sql.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author xuzhou
 * @version 1.0.0
 * @since 2022-11-25  12:03
 */
public class JsonDataService {
    public static void main(String[] args) {
        String str = "[\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188168994817\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5604\",\n" +
                "\t\t\"sn\": \"0107BBDD7464\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8C-11-18\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188215132161\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5605\",\n" +
                "\t\t\"sn\": \"0107BBDDB061\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-82-31-1A\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188257075201\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5606\",\n" +
                "\t\t\"sn\": \"0107BBDD760C\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-87-FF-62\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188303212546\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5607\",\n" +
                "\t\t\"sn\": \"0107BBDD0218\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8C-95-26\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188391292930\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5609\",\n" +
                "\t\t\"sn\": \"0107BBDD2943\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8B-09-BD\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188437430274\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5610\",\n" +
                "\t\t\"sn\": \"0107BBDD0C2C\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8A-47-B9\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1584025188529704962\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1583658540346437634\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5612\",\n" +
                "\t\t\"sn\": \"0107BBDDFC34\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-86-EA-24\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-10-23 11:33:26\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708035588097\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8562\",\n" +
                "\t\t\"sn\": \"1250BBCE8BF6\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8E-C9-F5\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708111085569\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8564\",\n" +
                "\t\t\"sn\": \"1250BBCE0B70\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8E-2C-E2\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708148834306\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8565\",\n" +
                "\t\t\"sn\": \"1250BBCE71CC\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8F-00-FE\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708186583041\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8566\",\n" +
                "\t\t\"sn\": \"1250BBCE348D\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-80-A1-44\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708224331778\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8567\",\n" +
                "\t\t\"sn\": \"1250BBCEFF84\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8F-08-6E\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708262080514\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8568\",\n" +
                "\t\t\"sn\": \"1250BBCEA930\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8D-BA-84\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708299829250\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8569\",\n" +
                "\t\t\"sn\": \"1250BBCEA5AD\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-80-FB-B7\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708379521026\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8571\",\n" +
                "\t\t\"sn\": \"1250BBCE471C\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-85-65-05\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1561887708496961537\",\n" +
                "\t\t\"productId\": \"1473863121050402818\",\n" +
                "\t\t\"orderId\": \"1561547725709942786\",\n" +
                "\t\t\"bomId\": \"1478975590513188865\",\n" +
                "\t\t\"machineCode\": \"MSG8574\",\n" +
                "\t\t\"sn\": \"1250BBCE2783\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-86-26-9C\",\n" +
                "\t\t\"createBy\": \"molly.gao\",\n" +
                "\t\t\"createTime\": \"2022-08-23 09:27:02\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1585463595647193090\",\n" +
                "\t\t\"productId\": \"1470594544214102018\",\n" +
                "\t\t\"orderId\": \"1582618321105399809\",\n" +
                "\t\t\"bomId\": \"1470929152130584578\",\n" +
                "\t\t\"machineCode\": \"RY0660\",\n" +
                "\t\t\"sn\": \"1281BBDDD4F4\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8C-A6-EF\",\n" +
                "\t\t\"createBy\": \"shenchuangchuang\",\n" +
                "\t\t\"createTime\": \"2022-10-27 10:49:12\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1585463595802382338\",\n" +
                "\t\t\"productId\": \"1470594544214102018\",\n" +
                "\t\t\"orderId\": \"1582618321105399809\",\n" +
                "\t\t\"bomId\": \"1470929152130584578\",\n" +
                "\t\t\"machineCode\": \"RY0663\",\n" +
                "\t\t\"sn\": \"1281BBDDAE73\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8B-17-CF\",\n" +
                "\t\t\"createBy\": \"shenchuangchuang\",\n" +
                "\t\t\"createTime\": \"2022-10-27 10:49:12\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1585463596406362114\",\n" +
                "\t\t\"productId\": \"1470594544214102018\",\n" +
                "\t\t\"orderId\": \"1582618321105399809\",\n" +
                "\t\t\"bomId\": \"1470929152130584578\",\n" +
                "\t\t\"machineCode\": \"RY0675\",\n" +
                "\t\t\"sn\": \"1281BBDD4ED3\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-85-48-70\",\n" +
                "\t\t\"createBy\": \"shenchuangchuang\",\n" +
                "\t\t\"createTime\": \"2022-10-27 10:49:12\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t}\n" +
                "]";
        JSONArray arrays = JSON.parseArray(str);
        for (Object array : arrays) {
            JsonData data = JSONObject.parseObject(JSON.toJSONString(array), JsonData.class);
            System.out.println(data.toSqlString());
        }

    }
}
