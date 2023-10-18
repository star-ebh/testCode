package com.example.file;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddStamp3 {
    public static void main(String[] args) {
        // 要操作的Word文档文件路径
        String filePath = "E:/R-SP-05-01-F05 04版 体系文件模板20230510.docx";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            // 判断文档类型（doc还是docx）
            XWPFDocument docx = new XWPFDocument(fis);
            XWPFHeader header = docx.createHeader(HeaderFooterType.DEFAULT);
            XWPFParagraph paragraph = header.createParagraph();

            // 插入图片
            XWPFRun run = paragraph.createRun();
            String imageFilePath = "E:\\yinzhang.png";
            run.addPicture(new FileInputStream(imageFilePath), XWPFDocument.PICTURE_TYPE_PNG, "image", Units.toEMU(100), Units.toEMU(400));

            FileOutputStream fos = new FileOutputStream("E:\\cccccccccccccccccccccccccc.docx");
            docx.write(fos);
            fos.close();
            docx.close();


            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
