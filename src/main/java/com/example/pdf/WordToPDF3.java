//package com.example.pdf;
//
//import com.documents4j.api.DocumentType;
//import com.documents4j.api.IConverter;
//import com.documents4j.job.LocalConverter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.xwpf.usermodel.*;
//import org.docx4j.Docx4J;
//import org.docx4j.fonts.IdentityPlusMapper;
//import org.docx4j.fonts.Mapper;
//import org.docx4j.fonts.PhysicalFonts;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
//
//import java.io.*;
//
//@Slf4j
//public class WordToPDF3 {
//    private static final IConverter CONVERTER = LocalConverter.builder().build();
//
//    public static void main(String[] args) {
////        "E:\\WordInsertPictures1.docx"
//        String inputFilePath = "E:\\WordInsertPictures1.docx";
//        String outputFilePath = "E:\\WordToPDF3.pdf";
//        try {
//
//            InputStream docxInputStream = deleteRevisions(inputFilePath);
//            OutputStream outputStream = new FileOutputStream(outputFilePath);
//            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(docxInputStream);
//            setFontMapper(mlPackage);
//            Docx4J.toPDF(mlPackage, outputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private static void setFontMapper(WordprocessingMLPackage mlPackage) throws Exception {
//        Mapper fontMapper = new IdentityPlusMapper();
//        //fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
//        fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
//        fontMapper.put("宋体（中文正文）", PhysicalFonts.get("SimSun"));
//        //fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
//        fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
//        //fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
//        fontMapper.put("等线", PhysicalFonts.get("SimSun"));
//        fontMapper.put("等线 Light", PhysicalFonts.get("SimSun"));
//        fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
//        //fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
//        //fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
//        //fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
//        fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
//        //fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
//        //fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
//        //fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
//        //fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
//        //解决宋体（正文）和宋体（标题）等的乱码问题
//        PhysicalFonts.put("PMingLiU", PhysicalFonts.get("SimSun"));
//        PhysicalFonts.put("新細明體", PhysicalFonts.get("SimSun"));
//
//
//        mlPackage.setFontMapper(fontMapper);
//    }
//    public static ByteArrayInputStream deleteRevisions(String inputFilePath) throws IOException {
//        try (FileInputStream fis = new FileInputStream(inputFilePath)) {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            // 创建一个XWPF文档对象
//            XWPFDocument document = new XWPFDocument(fis);
//            // 获取文档中的所有段落
//            for (XWPFParagraph paragraph : document.getParagraphs()) {
//                // 遍历段落中的所有文本运行
//                for (XWPFRun run : paragraph.getRuns()) {
//                    // 如果文本运行中包含批注，则删除该批注
//                    if (!run.getCTR().getCommentReferenceList().isEmpty()) {
//                        run.getCTR().setCommentReferenceArray(new CTMarkup[]{});
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
//                                    run.getCTR().setCommentReferenceArray(new CTMarkup[]{});
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
//        }
//    }
//
//    /**
//     * 通过documents4j 实现word转pdf
//     *
//     * @param sourcePath 源文件地址 如 /root/example.doc
//     * @param targetPath 目标文件地址 如 /root/example.pdf
//     */
//    public static void documents4jWordToPdf(String sourcePath, String targetPath) {
//        File inputWord = new File(sourcePath);
//        File outputFile = new File(targetPath);
//        try {
//            InputStream docxInputStream = new FileInputStream(inputWord);
//            OutputStream outputStream = new FileOutputStream(outputFile);
//
//            CONVERTER.convert(docxInputStream)
//                    .as(DocumentType.DOCX)
//                    .to(outputStream)
//                    .as(DocumentType.PDF).execute();
//            outputStream.close();
//            docxInputStream.close();
//
//            log.info("转换完毕 targetPath = {}", outputFile.getAbsolutePath());
//            CONVERTER.shutDown();
//        } catch (Exception e) {
//            log.error("[documents4J] word转pdf失败:{}", e.toString());
//        }
//    }
//}