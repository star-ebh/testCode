package com.example.erp.conditions.interfaces;

import com.example.erp.conditions.AbstractWrapper;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiPredicate;

public interface Compare extends Serializable {
    AbstractWrapper eq(boolean condition, String column, Object val);
}
