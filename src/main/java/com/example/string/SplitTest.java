package com.example.string;

import lombok.Data;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitTest {

    @Data
    static class SymbolSplit {
        private String field;
        private String symbol;
        private String val;
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("1.0"));
        BigDecimal paymentAmount = new BigDecimal(0);
        BigDecimal unpaidAmount = new BigDecimal(123.341);
        System.out.println(paymentAmount.compareTo(BigDecimal.ZERO) < 0);
//        symbol("test not in   ( 123 )");
    }

    private static SymbolSplit symbol(String filter) {
        String field = "";
        String symbol = "";
        String val = "";

        // 匹配字段名和比较符
        Pattern pattern = Pattern.compile("([\\w.]+)\\s+((?:not)?\\s*in|==|!=)");
        Matcher matcher = pattern.matcher(filter);
        if (matcher.find()) {
            field = matcher.group(1).trim();
            symbol = matcher.group(2).trim();
        }

        // 根据比较符判断值的提取方式
        if (symbol.equals("in") || symbol.equals("not in")) {
            pattern = Pattern.compile("\\s*\\((.*?)\\)");
            matcher = pattern.matcher(filter);
            if (matcher.find()) {
                val = matcher.group(1).trim();
            }
        } else {
            pattern = Pattern.compile("[=!<>]+\\s*(.*)");
            matcher = pattern.matcher(filter);
            if (matcher.find()) {
                val = matcher.group(1).trim();
            }
        }

        System.out.println(field);
        System.out.println(symbol);
        System.out.println(val);

        SymbolSplit symbolSplit = new SymbolSplit();
        symbolSplit.setSymbol(symbol);
        symbolSplit.setField(field);
        symbolSplit.setVal(val);
        return symbolSplit;
    }
}
