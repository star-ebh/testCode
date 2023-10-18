//package com.example.file;
//
//
//import org.apache.poi.util.Units;
//import org.apache.poi.xwpf.usermodel.*;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
//
//import java.io.*;
//
//public class AddStamp4 {
//    public static void main(String[] args) {
//        try (FileInputStream fileInputStream = new FileInputStream("path/to/your/document.docx");
//             XWPFDocument document = new XWPFDocument(fileInputStream)) {
//
//            // 获取页眉
//            XWPFHeader header = document.getHeaderList().get(0); // 假设只有一个页眉
//
//            // 读取图片文件
//            FileInputStream imageStream = new FileInputStream("path/to/image.jpg");
//            XWPFPictureData pictureData = document.addPictureData(imageStream, Document.PICTURE_TYPE_JPEG);
//            imageStream.close();
//
//            // 创建段落并添加图片
//            XWPFParagraph paragraph = header.createParagraph();
//            XWPFRun run = paragraph.createRun();
//            int width = 200; // 图片宽度（单位：像素）
//            int height = 100; // 图片高度（单位：像素）
//            run.addPicture(pictureData.getData(), Document.PICTURE_TYPE_JPEG, "path/to/image.jpg", Units.toEMU(width), Units.toEMU(height));
//
//            // 设置图片位置
//            CTPicture ctPicture = run.getCTR().getDrawingArray(0).getInlineArray(0).getGraphic().getGraphicData().getPic();
//            CTTransform2D ctTransform2D = ctPicture.getSpPr().getXfrm();
//            ctTransform2D.setOff(Units.toEMU(100), Units.toEMU(100)); // 设置图片偏移量（单位：EMU）
//
//            // 保存修改后的文档
//            FileOutputStream fileOutputStream = new FileOutputStream("path/to/your/modified/document.docx");
//            document.write(fileOutputStream);
//            fileOutputStream.close();
//
//            System.out.println("图片已添加到页眉并保存成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
