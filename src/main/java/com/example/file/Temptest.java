package com.example.file;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;

public class Temptest {
    public static void main(String[] args) throws Exception {
        System.out.println(new BigDecimal(12).compareTo(BigDecimal.ZERO) <= 0);
    }
}
