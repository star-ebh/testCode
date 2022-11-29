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
                "\t\t\"id\": \"1593483322469986306\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5923\",\n" +
                "\t\t\"sn\": \"0107BBD739AC\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8E-57-9E\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322537095169\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5924\",\n" +
                "\t\t\"sn\": \"0107BBD72C28\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-88-9E-D1\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322650341378\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5926\",\n" +
                "\t\t\"sn\": \"0107BBD78D54\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-82-E2-58\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322709061634\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5927\",\n" +
                "\t\t\"sn\": \"0107BBD7C637\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8E-03-7B\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322767781889\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5928\",\n" +
                "\t\t\"sn\": \"0107BBD7B4CE\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-85-99-C8\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322834890754\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5929\",\n" +
                "\t\t\"sn\": \"0107BBD76CB0\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-80-88-73\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322893611009\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5930\",\n" +
                "\t\t\"sn\": \"0107BBD7DB46\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-80-9D-90\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483322960719873\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5931\",\n" +
                "\t\t\"sn\": \"0107BBD7D6A0\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-84-D1-F0\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323027828738\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5932\",\n" +
                "\t\t\"sn\": \"0107BBD7200F\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-86-9F-88\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323157852162\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5934\",\n" +
                "\t\t\"sn\": \"0107BBD7D02B\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8D-89-A5\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323237543937\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5935\",\n" +
                "\t\t\"sn\": \"0107BBD7E95F\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8C-9F-77\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323304652802\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5936\",\n" +
                "\t\t\"sn\": \"0107BBD7575D\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-87-EE-BA\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323363373057\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5937\",\n" +
                "\t\t\"sn\": \"0107BBD7F6FB\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8D-64-90\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323430481922\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5938\",\n" +
                "\t\t\"sn\": \"0107BBD7DE94\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8A-6B-43\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323510173697\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5939\",\n" +
                "\t\t\"sn\": \"0107BBD7CB55\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8A-57-23\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323615031298\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5940\",\n" +
                "\t\t\"sn\": \"0107BBD7D540\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-86-A3-14\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323673751553\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5941\",\n" +
                "\t\t\"sn\": \"0107BBD7513C\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8D-B8-BD\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323736666114\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5942\",\n" +
                "\t\t\"sn\": \"0107BBD7FA86\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-84-6F-6B\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323816357889\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5943\",\n" +
                "\t\t\"sn\": \"0107BBD7CFD2\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8C-5E-04\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
                "\t\t\"updateBy\": null,\n" +
                "\t\t\"updateTime\": null\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"1593483323896049665\",\n" +
                "\t\t\"productId\": \"1529270082235199490\",\n" +
                "\t\t\"orderId\": \"1592324290208636930\",\n" +
                "\t\t\"bomId\": \"1530119995785367553\",\n" +
                "\t\t\"machineCode\": \"HD5944\",\n" +
                "\t\t\"sn\": \"0107BBD77146\",\n" +
                "\t\t\"macAddress\": \"40-2C-76-8C-C3-B9\",\n" +
                "\t\t\"createBy\": \"huiyuan.peng\",\n" +
                "\t\t\"createTime\": \"2022-11-18 13:56:43\",\n" +
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
