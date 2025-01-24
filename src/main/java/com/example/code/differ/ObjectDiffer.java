package com.example.code.differ;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectDiffer<T> {

    // 方法用于比较两个对象并返回差异字段及其对应的新旧值
    public Map<Field, Pair<Object, Object>> compareFields(T oldObject, T newObject) throws IllegalAccessException {
        Class<?> clazz = oldObject.getClass();
        Map<Field, Pair<Object, Object>> diffMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            // 设置访问权限，以便读取私有字段
            field.setAccessible(true);

            // 获取旧对象的字段值
            Object oldValue = field.get(oldObject);
            // 获取新对象的字段值
            Object newValue = field.get(newObject);

            // 比较字段值是否相等
            if (!Objects.equals(oldValue, newValue)) {
                // 若不相等，则将差异放入map中
                diffMap.put(field, new Pair<>(oldValue, newValue));
            }
        }

        return diffMap;
    }

    // 定义一个Pair类用于存储字段的新旧值对
    static class Pair<A, B> {
        A first;
        B second;

        Pair(A a, B b) {
            this.first = a;
            this.second = b;
        }
    }


    public static void main(String[] args) throws IllegalAccessException {
        TestObj oldObj = new TestObj("Alice",25,"New York","USA");
        TestObj newObj = new TestObj("Alice",35,"New York","USA");

        ObjectDiffer<TestObj> differ = new ObjectDiffer<>();
        Map<Field, Pair<Object, Object>> diffs = differ.compareFields(oldObj, newObj);

        for (Map.Entry<Field, Pair<Object, Object>> entry : diffs.entrySet()) {
            Field field = entry.getKey();
            System.out.println("Field: " + field.getName() + ", Old Value: " + entry.getValue().first + ", New Value: " + entry.getValue().second);
        }
    }

    @Data
    public static class TestObj {
        private String name;
        private Integer age;
        private String city;
        private String country;

        public TestObj(String name, Integer age, String city, String country) {
            this.name = name;
            this.age = age;
            this.city = city;
            this.country = country;
        }
    }
}
