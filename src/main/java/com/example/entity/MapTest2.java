package com.example.entity;

import cn.hutool.core.lang.Console;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapTest2 implements Serializable {

    /**
     * 校验长度Map
     */
    private Map<String, MapTest1> lengthVerifyMap;


    public static void main(String[] args) {
        MapTest2 mapTest2 = new MapTest2();
        MapTest1 mapTest1 = new MapTest1();
        mapTest1.setId("1");
        Map<String, MapTest1> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("1", mapTest1);
        mapTest2.setLengthVerifyMap(objectObjectHashMap);
        Console.log("1、mapTest2=>{}", mapTest2);
        System.gc();
        Console.log("2、mapTest2=>{}", mapTest2);
        objectObjectHashMap.clear();
        Console.log("3、mapTest2=>{}", mapTest2);
        System.gc();
        Console.log("4、mapTest2=>{}", mapTest2);
        List<String> list = new ArrayList<>();
        list.clear();
    }
}
