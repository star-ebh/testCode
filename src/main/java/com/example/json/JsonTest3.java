package com.example.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest3 {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        // 原始 JSON 数据
//        String json = "[\n" +
//                "    {\"id\":\"123\",\"naem\":\"一级\",\"praentId\":\"\"},\n" +
//                "    {\"id\":\"1234\",\"naem\":\"二级\",\"praentId\":\"123\"},\n" +
//                "    {\"id\":\"12345\",\"naem\":\"三级\",\"praentId\":\"1234\"},\n" +
//                "    {\"id\":\"12345601\",\"naem\":\"四级\",\"praentId\":\"12345\"},\n" +
//                "    {\"id\":\"12345602\",\"naem\":\"四级\",\"praentId\":\"12345\"},\n" +
//                "    {\"id\":\"1234560101\",\"naem\":\"五级60101\",\"praentId\":\"12345601\"},\n" +
//                "    {\"id\":\"1234560102\",\"naem\":\"五级60102\",\"praentId\":\"12345601\"},\n" +
//                "    {\"id\":\"1234560103\",\"naem\":\"五级60103\",\"praentId\":\"12345601\"},\n" +
//                "    {\"id\":\"1234560104\",\"naem\":\"五级60104\",\"praentId\":\"12345601\"},\n" +
//                "    {\"id\":\"1234560201\",\"naem\":\"五级60201\",\"praentId\":\"12345602\"},\n" +
//                "    {\"id\":\"1234560202\",\"naem\":\"五级60202\",\"praentId\":\"12345602\"},\n" +
//                "    {\"id\":\"1234560203\",\"naem\":\"五级60203\",\"praentId\":\"12345602\"},\n" +
//                "    {\"id\":\"1234560204\",\"naem\":\"五级60204\",\"praentId\":\"12345602\"}\n" +
//                "]";
//
//        // 将 JSON 数据解析为对象数组
//        JSONArray treeNodes = JSON.parseArray(json);
        JSONArray treeNodes = new JSONArray();

        for (int i = 0; i < 50000; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", String.valueOf(i));
            if (i == 0) {
                jsonObject.put("praentId", "");
            } else {
                jsonObject.put("praentId", String.valueOf(i - 1));
            }
            treeNodes.add(jsonObject);
        }
        // 构建节点映射表：以节点ID为键，节点对象为值
        Map<String, JSONObject> nodeMap = new HashMap<>();
        for (int i = 0; i < treeNodes.size(); i++) {
            JSONObject treeNode = treeNodes.getJSONObject(i);
            String nodeId = treeNode.getString("id");
            nodeMap.put(nodeId, treeNode);
        }
        // 遍历树，找出所有最末级数据
        List<JSONObject> leafNodes = new ArrayList<>();
        for (int i = 0; i < treeNodes.size(); i++) {
            JSONObject treeNode = treeNodes.getJSONObject(i);
            if (!hasChild(treeNode.getString("id"), nodeMap)) {
                leafNodes.add(treeNode);
            }
        }
        // 输出结果
        System.out.println(JSON.toJSONString(leafNodes));
        System.out.println("耗时：" + (System.currentTimeMillis() - time));
    }

    // 判断节点是否有子节点
    private static boolean hasChild(String nodeId, Map<String, JSONObject> nodeMap) {
        for (JSONObject node : nodeMap.values()) {
            if (node.getString("praentId").equals(nodeId)) {
                return true;
            }
        }
        return false;
    }
}
