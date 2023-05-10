package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hkh
 * @version 1.0.0
 * @Description Test1
 * @createTime 2022年05月07日 14:11:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapTest1 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("销毁了");
    }
}