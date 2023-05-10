package com.example.file;

import cn.hutool.core.io.FileUtil;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InsertImageIntoWord {
    public static void main(String[] args) throws Exception {
        String fileName = "E:\\R-COP-02-01-03 试制管理规定_20220606.doc";
        String mimeType = FileUtil.getMimeType(fileName);
        //if ("docx".equals(mimeType)) {
        // 读取已存在的 Word 文档
        XWPFDocument doc = new XWPFDocument(new FileInputStream(fileName));
        // 获取文档中的最后一段落
        XWPFParagraph p = doc.getParagraphs().get(doc.getParagraphs().size() - 1);
        // 创建文本运行对象
        XWPFRun run = p.createRun();
        // 读取印章图片
        BufferedImage img = ImageIO.read(new File("E:\\yinzhang.png"));
        // 将图片插入到 Word 文档中
        run.addPicture(new FileInputStream("E:\\yinzhang.png"), XWPFDocument.PICTURE_TYPE_PNG, "image.png", Units.toEMU(img.getWidth()), Units.toEMU(img.getHeight()));
        // 输出 Word 文档
        FileOutputStream out = new FileOutputStream("E:\\document.docx");
        doc.write(out);
        out.close();
        doc.close();
        //} else {
        //
        //}

    }
}
