package com.example.erp.conditions;

public abstract class AbstractWrapper {

    protected StringBuilder sql;

    public AbstractWrapper() {
        sql = new StringBuilder();
    }

}
