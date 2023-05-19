package com.example.erp.conditions;

import lombok.Data;

@Data
public class Filter {

    /**
     * 左边
     */
    private String left;

    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 比较 = > < >= <= != like in not in between
     */
    private String compare;
    /**
     * 值
     */
    private Object value;
    /**
     * 正确
     */
    private String right;
    /**
     * 逻辑
     */
    private String logic;

}
