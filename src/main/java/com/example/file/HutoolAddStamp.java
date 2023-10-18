package com.example.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HutoolAddStamp {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("E:/R-SP-05-01-F05 04版 体系文件模板20230510.docx");
//
//            // 判断文档类型（doc还是docx）
//            if (filePath.endsWith(".docx")) {
//                XWPFDocument docx = new XWPFDocument(fis);
        Word07Writer writer = new Word07Writer();
        // 添加段落（标题）
        writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), "我是第一部分", "我是第二部分");
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 22), "我是正文第一部分", "我是正文第二部分");
        // 写出到文件
        writer.flush(FileUtil.file("E:\\AddStamp1111.docx"));
        // 关闭
        writer.close();
    }
}
