package com.example.erp.conditions.interfaces;

import java.io.Serializable;

public interface Join<Children> extends Serializable {

    /**
     * ignore
     */
    default Children or() {
        return or(true);
    }

    /**
     * 拼接 OR
     *
     * @param condition 执行条件
     * @return children
     */
    Children or(boolean condition);


}
