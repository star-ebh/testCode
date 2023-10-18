package com.example.file;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class WordWriter {
    public static void main(String[] args) throws Exception {
        // 读取已存在的 Word 文档
        XWPFDocument doc = new XWPFDocument(new FileInputStream("E:\\R-SP-05-01-F05 04版 体系文件模板20230510.docx"));
        // 获取文档中的所有页
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        // 读取印章图片
//        BufferedImage img = ImageIO.read(new File("E:\\yinzhang.png"));
        // 遍历所有页
        for (XWPFParagraph p : paragraphs) {
            // 创建文本运行对象
            XWPFRun run = p.createRun();
            // 将图片插入到 Word 文档中
            run.addPicture(new FileInputStream("E:\\yinzhang.png"), XWPFDocument.PICTURE_TYPE_PNG, "image.png", Units.toEMU(120), Units.toEMU(80));
            // 设置文本运行对象的字体大小和颜色
            run.setFontSize(100);
            run.setColor("FFFFFFFF");
            // 设置文本运行对象的旋转角度和透明度
            run.setTextPosition(0);
            //run.setVerticalAlignment(TextAlignment.CENTER);
            run.setEmbossed(true);
            run.setImprinted(true);
        }
        // 输出 Word 文档
        FileOutputStream out = new FileOutputStream("E:\\WordWriter.docx");
        doc.write(out);
        out.close();
        doc.close();
    }
}
