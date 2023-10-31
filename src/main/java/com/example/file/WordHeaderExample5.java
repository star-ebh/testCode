package com.example.file;


import com.aspose.words.*;

import java.io.FileInputStream;

public class WordHeaderExample5 {

    public static void main(String[] args) throws Exception {
        Document doc = new Document("E:\\RS-IC-01-0001 01版 内部控制管理制度.doc");
        HeaderFooter header = doc.getFirstSection().getHeadersFooters().getByHeaderFooterType(HeaderFooterType.HEADER_PRIMARY);
        // 创建段落
        Paragraph paragraph = new Paragraph(doc);
        header.appendChild(paragraph);
        Shape shape = new Shape(doc, ShapeType.IMAGE);
        shape.getImageData().setImage("E:\\yinzhang.png");
        shape.setWrapType(WrapType.NONE);
        shape.setWidth(120);
        shape.setHeight(80);
        shape.setLeft(375D);
        shape.setTop(-35D);
// 将图片添加到段落中
        paragraph.appendChild(shape);
        doc.save("E:\\内部控制管理制度output.doc");
        Document newDoc = new Document(new FileInputStream("E:\\内部控制管理制度output.doc"));
        newDoc.save("E:\\内部控制管理制度output.pdf", SaveFormat.PDF);

    }


}
