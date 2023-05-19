package com.example.erp.conditions;

import com.alibaba.fastjson.JSON;
import com.example.erp.conditions.enmus.SqlKeyword;
import com.example.erp.conditions.interfaces.Compare;
import com.example.erp.conditions.interfaces.Func;
import com.example.erp.conditions.interfaces.Join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class CommonWrapper extends AbstractWrapper implements Compare<CommonWrapper, String>, Join<CommonWrapper>, Func<CommonWrapper, String> {

    public static void main(String[] args) {
        CommonWrapper commonWrapper = new CommonWrapper();
        commonWrapper.ne("FBillNo", "XSTHD009931")
                .ne("FBillNo", "XSTHD009955");
        System.out.println(JSON.toJSONString(commonWrapper.sqlFilterList));

    }


    public CommonWrapper() {
        sqlFilterList = new ArrayList<>();
    }

    @Override
    public CommonWrapper eq(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, val);
            filter.setCompare(SqlKeyword.EQ.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper ne(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, val);
            filter.setCompare(SqlKeyword.NE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper gt(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, val);
            filter.setCompare(SqlKeyword.GT.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper ge(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, val);
            filter.setCompare(SqlKeyword.GE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper lt(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, val);
            filter.setCompare(SqlKeyword.LT.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper le(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, val);
            filter.setCompare(SqlKeyword.LE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper like(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, String.format(Locale.ROOT, "%%%s%%", val));
            filter.setCompare(SqlKeyword.LIKE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper notLike(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, String.format(Locale.ROOT, "%%%s%%", val));
            filter.setCompare(SqlKeyword.NOT_LIKE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper likeLeft(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, String.format(Locale.ROOT, "%%%s", val));
            filter.setCompare(SqlKeyword.NOT_LIKE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper likeRight(boolean condition, String column, Object val) {
        if (condition) {
            Filter filter = builderFilter(column, String.format(Locale.ROOT, "%s%%", val));
            filter.setCompare(SqlKeyword.NOT_LIKE.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper or(boolean condition) {
        if (!condition) {
            return this;
        }
        if (sqlFilterList.isEmpty()) {
            return this;
        }
        Filter filter = sqlFilterList.get(sqlFilterList.size() - 1);
        filter.setLogic(SqlKeyword.OR.getSqlSegment());
        return this;
    }

    @Override
    public CommonWrapper in(boolean condition, String column, Collection<?> coll) {
        if (condition) {
            String joinString = String.join(",", coll.stream().map(Object::toString).toArray(String[]::new));
            Filter filter = builderFilter(column, joinString, "(", ")");
            filter.setCompare(SqlKeyword.IN.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper in(boolean condition, String column, Object... values) {
        if (condition) {
            // q:转集合
            String joinString = String.join(",", Arrays.stream(values).map(Object::toString).toArray(String[]::new));
            Filter filter = builderFilter(column, joinString, "(", ")");
            filter.setCompare(SqlKeyword.IN.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper notIn(boolean condition, String column, Collection<?> coll) {
        if (condition) {
            String joinString = String.join(",", coll.stream().map(Object::toString).toArray(String[]::new));
            Filter filter = builderFilter(column, joinString, "(", ")");
            filter.setCompare(SqlKeyword.NOT_IN.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }

    @Override
    public CommonWrapper notIn(boolean condition, String column, Object... values) {
        if (condition) {
            // q:转集合
            String joinString = String.join(",", Arrays.stream(values).map(Object::toString).toArray(String[]::new));
            Filter filter = builderFilter(column, joinString, "(", ")");
            filter.setCompare(SqlKeyword.NOT_IN.getSqlSegment());
            filter.setLogic(SqlKeyword.AND.getSqlSegment());
            sqlFilterList.add(filter);
        }
        return this;
    }


    private Filter builderFilter(String column, Object val) {
        Filter filter = new Filter();
        filter.setLeft("");
        filter.setRight("");
        filter.setFieldName(column);
        filter.setValue(val);
        return filter;
    }

    private Filter builderFilter(String column, Object val, String left, String right) {
        Filter filter = new Filter();
        filter.setLeft(left);
        filter.setRight(right);
        filter.setFieldName(column);
        filter.setValue(val);
        return filter;
    }
}
