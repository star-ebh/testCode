package com.example.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;

public class JsonTest {
//    public static void main(String[] args) {
//        String test1 = "{\n" +
//                "            \"title\": \"每日飞书小知识\",\n" +
//                "            \"cover\": {\n" +
//                "                \"origin_cover\": \"https://sample.com/sample.jpg\",\n" +
//                "                \"message_cover\": \"https://sample.com/sample.jpg\",\n" +
//                "                \"article_cover\": \"https://sample.com/sample.jpg\"\n" +
//                "            },\n" +
//                "            \"content\": \"<p>content here</p>\",\n" +
//                "            \"external_link_url\": \"https://www.sample.cn\",\n" +
//                "            \"article_type\": 1,\n" +
//                "            \"content_source_url\": \"www.sample.com\",\n" +
//                "            \"author\": \"飞书小助手\",\n" +
//                "            \"i18n_title\": {\n" +
//                "                \"zh_cn\": \"中文内容\",\n" +
//                "                \"en_us\": \"英文内容\",\n" +
//                "                \"ja_jp\": \"日本語の内容\"\n" +
//                "            },\n" +
//                "            \"i18n_content\": {\n" +
//                "                \"zh_cn\": \"中文内容\",\n" +
//                "                \"en_us\": \"英文内容\",\n" +
//                "                \"ja_jp\": \"日本語の内容\"\n" +
//                "            },\n" +
//                "            \"i18n_author\": {\n" +
//                "                \"zh_cn\": \"中文内容\",\n" +
//                "                \"en_us\": \"英文内容\",\n" +
//                "                \"ja_jp\": \"日本語の内容\"\n" +
//                "            },\n" +
//                "            \"article_config\": {\n" +
//                "                \"allow_comment\": true,\n" +
//                "                \"allow_forward\": true,\n" +
//                "                \"share_type\": 1,\n" +
//                "                \"comment_display_type\": 1\n" +
//                "            },\n" +
//                "            \"digest\": \"这里是内容摘要\",\n" +
//                "            \"i18n_digest\": {\n" +
//                "                \"zh_cn\": \"中文内容\",\n" +
//                "                \"en_us\": \"英文内容\",\n" +
//                "                \"ja_jp\": \"日本語の内容\"\n" +
//                "            }\n" +
//                "        }";
//        CreateMessagesDTO createMessagesDTO = JSON.parseObject(test1,CreateMessagesDTO.class);
//        System.out.println(JSON.toJSONString(createMessagesDTO));
//    }

    public static <R> Result<R> buildData(String responseStr) {
        Type type = new TypeReference<Result<R>>() {
        }.getType();
        return JSON.parseObject(responseStr, type);
    }
}
