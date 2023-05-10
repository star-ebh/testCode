package com.example.file;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class WordWriterDoc {
    public static void main(String[] args) throws Exception {
        // Load Word document
        XWPFDocument doc = new XWPFDocument(new FileInputStream("E:\\R-COP-02-01-03 试制管理规定_20220606.doc"));

        // Get the first section of the document
        XWPFHeader header = doc.getHeaderList().get(0);

        // Add a new paragraph to the header
        XWPFParagraph para = header.createParagraph();

        // Add the image of the stamp to the paragraph
        XWPFRun run = para.createRun();
        run.addPicture(new FileInputStream("E:\\yinzhang.png"), XWPFDocument.PICTURE_TYPE_PNG, "stamp.png", Units.toEMU(120), Units.toEMU(80));

        // Save the document
        doc.write(new FileOutputStream("E:\\AddStamp.docx"));
        doc.close();
    }
}
