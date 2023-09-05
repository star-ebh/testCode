package com.example.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonTest2 {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        // 原始 JSON 数据
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
        List<JSONObject> nodeMap = new ArrayList<>();
        for (int i = 0; i < treeNodes.size(); i++) {
            nodeMap.add(treeNodes.getJSONObject(i));

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
    private static boolean hasChild(String nodeId, List<JSONObject> nodeMap) {
        for (JSONObject node : nodeMap) {
            if (node.getString("praentId").equals(nodeId)) {
                return true;
            }
        }
        return false;
    }
}
