package com.example.erp.conditions;

import java.io.Serializable;

@FunctionalInterface
public interface ISqlSegment extends Serializable {

    /**
     * SQL 片段
     */
    String getSqlSegment();

}

