//package com.example.file;
//
//import org.apache.poi.hssf.usermodel.HeaderFooter;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.usermodel.Picture;
//import org.apache.poi.hwpf.usermodel.Range;
//import org.apache.poi.sl.usermodel.PictureData;
//import org.apache.poi.util.Units;
//import org.apache.poi.wp.usermodel.HeaderFooterType;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFHeader;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class InsertHeaderImage {
//    public static void main(String[] args) {
//        // 要操作的Word文档文件路径
//        String filePath = "path_to_your_word_file.docx";
//
//        try {
//            FileInputStream fis = new FileInputStream(filePath);
//
//            // 判断文档类型（doc还是docx）
//            if (filePath.endsWith(".docx")) {
//                XWPFDocument docx = new XWPFDocument(fis);
//                XWPFHeader header = docx.createHeader(HeaderFooterType.DEFAULT);
//                XWPFParagraph paragraph = header.createParagraph();
//
//                // 插入图片
//                XWPFRun run = paragraph.createRun();
//                String imageFilePath = "path_to_your_image.jpg";
//                run.addPicture(new FileInputStream(imageFilePath), XWPFDocument.PICTURE_TYPE_JPEG, "image", Units.toEMU(100), Units.toEMU(100));
//
//                FileOutputStream fos = new FileOutputStream(filePath);
//                docx.write(fos);
//                fos.close();
//                docx.close();
//            } else if (filePath.endsWith(".doc")) {
//                HWPFDocument doc = new HWPFDocument(fis);
//                HeaderFooter header = doc.getHeaderStory();
//                Range range = header.getRange();
//                Picture picture = new Picture();
//
//                // 插入图片
//                String imageFilePath = "path_to_your_image.jpg";
//                FileInputStream imageStream = new FileInputStream(imageFilePath);
//                picture.setImage(new PictureData(imageStream, PictureData.PictureType.JPEG));
//                picture.setDimensions(100, 100);
//                range.insertBefore(picture);
//
//                FileOutputStream fos = new FileOutputStream(filePath);
//                doc.write(fos);
//                fos.close();
//                doc.close();
//            }
//
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
