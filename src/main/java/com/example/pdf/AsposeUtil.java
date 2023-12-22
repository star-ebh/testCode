//package com.example.pdf;
//
//import cn.hutool.core.io.IoUtil;
//import com.aspose.words.Document;
//import com.aspose.words.License;
//import com.aspose.words.SaveFormat;
//import lombok.SneakyThrows;
//import org.apache.http.Header;
//import org.apache.poi.util.Units;
//import org.apache.poi.xwpf.usermodel.*;
//import org.apache.xmlbeans.XmlException;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
//import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//
//public class AsposeUtil {
//    /**
//     * 加载license 用于破解 不生成水印
//     *
//     * @author LCheng
//     * @date 2020/12/25 13:51
//     */
//    @SneakyThrows
//    private static void getLicense() {
//        try (InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("License.xml")) {
//            License license = new License();
//            license.setLicense(is);
//        }
//    }
//
//    /**
//     * word转pdf
//     *
//     * @param wordPath word文件保存的路径
//     * @param pdfPath  转换后pdf文件保存的路径
//     * @author LCheng
//     * @date 2020/12/25 13:51
//     */
//    @SneakyThrows
//    public static void wordToPdf(String wordPath, String pdfPath) {
////        getLicense();
//        File file = new File(pdfPath);
//        try (FileOutputStream os = new FileOutputStream(file)) {
//            Document doc = new Document(addImage(deleteRevisions(new FileInputStream(wordPath))));
//            doc.save(os, SaveFormat.PDF);
//        }
//    }
//
//    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject, String deskFileName, int width,
//                                                int height, int leftOffset, int topOffset, boolean behind) {
//        String anchorXML =
//                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
//                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0)
//                        + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">" + "<wp:simplePos x=\"0\" y=\"0\"/>"
//                        + "<wp:positionH relativeFrom=\"column\">" + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
//                        + "</wp:positionH>" + "<wp:positionV relativeFrom=\"paragraph\">" + "<wp:posOffset>" + topOffset
//                        + "</wp:posOffset>" + "</wp:positionV>" + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
//                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>" + "<wp:wrapNone/>"
//                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
//                        + "</wp:anchor>";
//
//        CTDrawing drawing = null;
//        try {
//            drawing = CTDrawing.Factory.parse(anchorXML);
//        } catch (XmlException e) {
//            e.printStackTrace();
//        }
//        CTAnchor anchor = drawing.getAnchorArray(0);
//        anchor.setGraphic(ctGraphicalObject);
//        return anchor;
//    }
//
//    private static ByteArrayInputStream addImage(ByteArrayInputStream byteArrayInputStream) throws Exception {
//        XWPFDocument document = new XWPFDocument(byteArrayInputStream);
//        XWPFHeader header = document.getHeaderList().get(0);
//        XWPFParagraph paragraph = header.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        // 设置图片
//        InputStream imageInputStream = new FileInputStream("E:\\yinzhang.png");
//        run.addPicture(imageInputStream, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, "TEST",
//                Units.toEMU(120), Units.toEMU(80));
//        // 2. 获取到图片数据
//        CTDrawing drawing = run.getCTR().getDrawingArray(0);
//        CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();
//
//        // 拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
//        CTAnchor anchor = getAnchorWithGraphic(graphicalobject, "TEST1", Units.toEMU(120), Units.toEMU(80),// 图片大小
//                // 相对当前段落位置 需要计算段落已有内容的左偏移
//                Units.toEMU(350), Units.toEMU(-12), false);
//        // 添加浮动属性
//        drawing.setAnchorArray(new CTAnchor[] {anchor});
//        // 删除行内属性
//        drawing.removeInline(0);
//        java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();
//        document.write(byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//        byteArrayOutputStream.close();
//        imageInputStream.close();
//        return  new ByteArrayInputStream(byteArray);
//    }
//    /**
//     * 删除修订
//     *
//     * @param fis FIS
//     * @return {@link ByteArrayInputStream}
//     */
//    public static ByteArrayInputStream deleteRevisions(InputStream fis) throws Exception {
//        try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            // 创建一个XWPF文档对象
//            XWPFDocument document = new XWPFDocument(fis);
//            // 获取文档中的所有段落
//            for (XWPFParagraph paragraph : document.getParagraphs()) {
//                // 遍历段落中的所有文本运行
//                for (XWPFRun run : paragraph.getRuns()) {
//                    // 如果文本运行中包含批注，则删除该批注
//                    if (!run.getCTR().getCommentReferenceList().isEmpty()) {
//                        run.getCTR().setCommentReferenceArray(new CTMarkup[] {});
//                    }
//                }
//            }
//            // 清除文档中所有表格中的批注
//            for (XWPFTable table : document.getTables()) {
//                for (XWPFTableRow row : table.getRows()) {
//                    for (XWPFTableCell cell : row.getTableCells()) {
//                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
//                            for (XWPFRun run : paragraph.getRuns()) {
//                                if (!run.getCTR().getCommentReferenceList().isEmpty()) {
//                                    run.getCTR().setCommentReferenceArray(new CTMarkup[] {});
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            // 将文档写入输出流
//            document.write(byteArrayOutputStream);
//            byte[] byteArray = byteArrayOutputStream.toByteArray();
//            byteArrayOutputStream.close();
//            return new ByteArrayInputStream(byteArray);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception("word转pdf失败");
//        } finally {
//            IoUtil.close(fis);
//        }
//    }
//    public static void main(String[] args) {
//        String inputFilePath = "E:\\R-SP-05-01-F05-IT-test - 副本.docx";
//        String outputFilePath = "E:\\AsposeUtil.pdf";
//        wordToPdf(inputFilePath, outputFilePath);
//        System.out.println("success");
//    }
//}
