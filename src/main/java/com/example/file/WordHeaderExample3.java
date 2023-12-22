//package com.example.file;
//
//import cn.hutool.core.io.FileUtil;
//import com.aspose.words.SaveFormat;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ooxml.POIXMLTypeLoader;
//import org.apache.poi.ooxml.util.DocumentHelper;
//import org.apache.poi.util.Units;
//import org.apache.poi.xwpf.usermodel.*;
//import org.apache.xmlbeans.XmlException;
//import org.apache.xmlbeans.XmlObject;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
//import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.*;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
//import org.xml.sax.InputSource;
//
//import java.io.*;
//
//public class WordHeaderExample3 {
//    public static ByteArrayInputStream deleteRevisions(InputStream fis) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        // 创建一个XWPF文档对象
//        XWPFDocument document = new XWPFDocument(fis);
//        // 获取文档中的所有段落
//        for (XWPFParagraph paragraph : document.getParagraphs()) {
//            // 遍历段落中的所有文本运行
//            for (XWPFRun run : paragraph.getRuns()) {
//                // 如果文本运行中包含批注，则删除该批注
//                if (!run.getCTR().getCommentReferenceList().isEmpty()) {
//                    run.getCTR().setCommentReferenceArray(new CTMarkup[]{});
//                }
//            }
//        }
//        // 清除文档中所有表格中的批注
//        for (XWPFTable table : document.getTables()) {
//            for (XWPFTableRow row : table.getRows()) {
//                for (XWPFTableCell cell : row.getTableCells()) {
//                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
//                        for (XWPFRun run : paragraph.getRuns()) {
//                            if (!run.getCTR().getCommentReferenceList().isEmpty()) {
//                                run.getCTR().setCommentReferenceArray(new CTMarkup[]{});
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        // 将文档写入输出流
//        document.write(byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//        byteArrayOutputStream.close();
//        return new ByteArrayInputStream(byteArray);
//    }
//
//    public static void main(String[] args) throws Exception {
////        XWPFDocument document = new XWPFDocument(deleteRevisions(new FileInputStream("E:\\RS-FIN-02-0001 财务资料外发管理办法.docx")));
//        XWPFDocument document = new XWPFDocument(deleteRevisions(new FileInputStream("E:\\RS-IC-01-0001 01版 内部控制管理制度.doc")));
//
//        XWPFHeader header = document.getHeaderList().get(0);
//        XWPFParagraph paragraph = header.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        // 添加浮动图片
//        InputStream in = new FileInputStream("E:\\yinzhang.png");
//        run.addPicture(in, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, "TEST", Units.toEMU(120), Units.toEMU(80));
//        in.close();
//        // 2. 获取到图片数据
//        CTDrawing drawing = run.getCTR().getDrawingArray(0);
//        CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();
//        //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
//        CTAnchor anchor = getAnchorWithGraphic(graphicalobject, "TEST1",
//                Units.toEMU(120), Units.toEMU(80),//图片大小
//                Units.toEMU(350), Units.toEMU(75), false);//相对当前段落位置 需要计算段落已有内容的左偏移
//        drawing.setAnchorArray(new CTAnchor[]{anchor});//添加浮动属性
//        drawing.removeInline(0);//删除行内属性
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        document.write(byteArrayOutputStream);
//        document.close();
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//        File newFile = new File("E:\\test.docx");
//        FileUtil.writeBytes(byteArray, newFile);
//        com.aspose.words.Document doc = new com.aspose.words.Document(new FileInputStream("E:\\test.docx"));
//        doc.save("E:\\test.pdf", SaveFormat.PDF);
//    }
//
//    /**
//     * @param ctGraphicalObject 图片数据
//     * @param deskFileName      图片描述
//     * @param width             宽
//     * @param height            高
//     * @param leftOffset        水平偏移 left
//     * @param topOffset         垂直偏移 top
//     * @param behind            文字上方，文字下方
//     * @return
//     * @throws Exception
//     */
//    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
//                                                String deskFileName, int width, int height,
//                                                int leftOffset, int topOffset, boolean behind) {
//        // paragraph / page
//        String anchorXML =
//                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
//                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0) + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
//                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
//                        + "<wp:positionH relativeFrom=\"column\">"
//                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
//                        + "</wp:positionH>"
//                        + "<wp:positionV relativeFrom=\"page\">"
//                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
//                        "</wp:positionV>"
//                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
//                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
//                        + "<wp:wrapNone/>"
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
////    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
////                                                String deskFileName, int width, int height,
////                                                int leftOffset, int topOffset, boolean behind) {
////        // paragraph / page
////        String anchorXML =
////                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
////                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0) + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
////                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
////                        + "<wp:positionH relativeFrom=\"column\">"
////                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
////                        + "</wp:positionH>"
////                        + "<wp:positionV relativeFrom=\"paragraph\">"
////                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
////                        "</wp:positionV>"
////                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
////                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
////                        + "<wp:wrapNone/>"
////                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
////                        + "</wp:anchor>";
////
////        CTDrawing drawing = null;
////        try {
////            drawing = CTDrawing.Factory.parse(anchorXML);
////        } catch (XmlException e) {
////            e.printStackTrace();
////        }
////        CTAnchor anchor = drawing.getAnchorArray(0);
////        anchor.setGraphic(ctGraphicalObject);
////        return anchor;
////    }
//
////    public static void convertWordToPdf(InputStream fs, String desc) {
////        try {
////            //create document object to wrap the file inputstream object
////            XWPFDocument doc = new XWPFDocument(fs);
////            //72 units=1 inch
////            com.itextpdf.text.Document pdfDoc = new com.itextpdf.text.Document(PageSize.A4, 72, 72, 72, 72);
////            //create a pdf writer object to write text to mypdf.pdf file
////            PdfWriter pwriter = PdfWriter.getInstance(pdfDoc, new FileOutputStream(desc));
////            //specify the vertical space between the lines of text
////            pwriter.setInitialLeading(20);
////            //get all paragraphs from word docx
////            List<XWPFParagraph> plist = doc.getParagraphs();
////
////            //open pdf document for writing
////            pdfDoc.open();
////            for (int i = 0; i < plist.size(); i++) {
////                //read through the list of paragraphs
////                XWPFParagraph pa = plist.get(i);
////                //get all run objects from each paragraph
////                List<XWPFRun> runs = pa.getRuns();
////                //read through the run objects
////                for (int j = 0; j < runs.size(); j++) {
////                    XWPFRun run = runs.get(j);
////                    //get pictures from the run and add them to the pdf document
////                    List<XWPFPicture> piclist = run.getEmbeddedPictures();
////                    //traverse through the list and write each image to a file
////                    Iterator<XWPFPicture> iterator = piclist.iterator();
////                    while (iterator.hasNext()) {
////                        XWPFPicture pic = iterator.next();
////                        XWPFPictureData picdata = pic.getPictureData();
////                        byte[] bytepic = picdata.getData();
////                        Image imag = Image.getInstance(bytepic);
////                        pdfDoc.add(imag);
////
////                    }
////                    //get color code
////                    int color = getCode(run.getColor());
////                    //construct font object
////                    Font f = null;
////                    if (run.isBold() && run.isItalic())
////                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLDITALIC, new BaseColor(color));
////                    else if (run.isBold())
////                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLD, new BaseColor(color));
////                    else if (run.isItalic())
////                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.ITALIC, new BaseColor(color));
////                    else if (run.isStrike())
////                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.STRIKETHRU, new BaseColor(color));
////                    else
////                        f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.NORMAL, new BaseColor(color));
////                    //construct unicode string
////                    String text = run.getText(-1);
////                    byte[] bs;
////                    if (text != null) {
////                        bs = text.getBytes();
////                        String str = new String(bs, "UTF-8");
////                        //add string to the pdf document
////                        Chunk chObj1 = new Chunk(str, f);
////                        pdfDoc.add(chObj1);
////                    }
////
////                }
////                //output new line
////                pdfDoc.add(new Chunk(Chunk.NEWLINE));
////            }
////            //close pdf document
////            pdfDoc.close();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//    public static int getCode(String code) {
//        int colorCode;
//        if (code != null)
//            colorCode = Long.decode("0x" + code).intValue();
//        else
//            colorCode = Long.decode("0x000000").intValue();
//        return colorCode;
//    }
//}
