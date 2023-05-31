//package com.example.file;
//
//import com.spire.doc.Document;
//import com.spire.doc.FileFormat;
//import com.spire.doc.HeaderFooter;
//import com.spire.doc.Section;
//import com.spire.doc.documents.Paragraph;
//import com.spire.doc.documents.PictureColor;
//import com.spire.doc.documents.TextWrappingStyle;
//import com.spire.doc.fields.DocPicture;
//
//import java.awt.*;
//
//public class AddStamp {
//    public static void main(String[] args) {
//        //加载示例文档
//        Document doc = new Document();
//        doc.loadFromFile("E:\\R-COP-02-01-03 试制管理规定_20220606.doc");
//        //获取指定段落
//        Section section = doc.getSections().get(0);
//        HeaderFooter header = section.getHeadersFooters().getHeader();
//        Paragraph paragraph = header.getParagraphs().get(0);
//        //添加电子章图片
//        DocPicture picture = paragraph.appendPicture("E:\\yinzhang.png");
//
//        //指定电子章位置
//        picture.setHorizontalPosition(380f);
//        picture.setVerticalPosition(-10f);
//
//        //设置电子章大小
//        picture.setWidth(120);
//        picture.setHeight(80);
//        //设置图片浮于文字上方 In_Front_Of_Text
//        picture.setTextWrappingStyle(TextWrappingStyle.In_Front_Of_Text);
//        //保存文档
//        doc.saveToFile("E:\\AddStamp.docx", FileFormat.Docx);
//        doc.saveToFile("E:\\AddStamp.pdf", FileFormat.PDF);
//        doc.dispose();
//    }
//}
