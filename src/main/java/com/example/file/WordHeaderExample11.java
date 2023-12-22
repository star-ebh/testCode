package com.example.file;

import cn.hutool.core.io.IoUtil;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;

import java.io.*;
import java.util.Objects;

public class WordHeaderExample11 {
    public static void main(String[] args) throws Exception {
//        String fileName = "Q:\\Downloads\\原文件.docx";
        String fileName = "Q:\\Downloads\\RS-AD-02-0003 01版 国内员工差旅管理规定.docx";
        String newFileName = "Q:\\Downloads\\RS-AD-02-0003 01版 国内员工差旅管理规定-test.docx";
        byte[] byteArray = deleteRevisions(new FileInputStream(fileName));
        ByteArrayInputStream wordInputStream = new ByteArrayInputStream(byteArray);
        XWPFDocument document = new XWPFDocument(wordInputStream);
        XWPFHeader header = document.getHeaderList().get(0);
        System.out.println("页眉段落数=>" + header.getParagraphs().size());
        for (int i = header.getParagraphs().size() - 1; i >= 0; i--) {
            XWPFParagraph itemParagraph = header.getParagraphs().get(i);
            System.out.println("页眉段落Runs数=>" + itemParagraph.getRuns().size());
            boolean isRemoveParagraph = true;
            for (XWPFRun itemRun : itemParagraph.getRuns()) {
                if (Objects.nonNull(itemRun.getCTR())) {
                    CTDrawing itemDrawing = itemRun.getCTR().getDrawingArray(0);
                    if (Objects.nonNull(itemDrawing)) {
                        isRemoveParagraph = false;
                    }
                }
            }
            if (isRemoveParagraph) {
                header.removeParagraph(itemParagraph);
            }
        }
//        for (XWPFParagraph paragraph : header.getParagraphs()) {
//            System.out.println("页眉段落Runs数=>" + paragraph.getRuns().size());
//            System.out.println("页眉段落内容=>" + paragraph.getText());
//            header.removeParagraph(paragraph);
//        }
        document.write(new FileOutputStream(newFileName));
        document.close();
    }


    private static Coordinate getPosition(IConverter converter, byte[] byteArray) throws IOException {
        // 转换PDF计算标记位置
        ByteArrayInputStream pdfInputStream = new ByteArrayInputStream(byteArray);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        converter.convert(pdfInputStream).as(DocumentType.DOCX).to(byteArrayOutputStream).as(DocumentType.PDF).execute();
        byte[] newByteArray = byteArrayOutputStream.toByteArray();
        IoUtil.close(byteArrayOutputStream);
        ByteArrayInputStream newPdfInputStream = new ByteArrayInputStream(newByteArray);
        MyPDFTextStripper stripper = new MyPDFTextStripper();
        stripper.setStartPage(0);
        stripper.setEndPage(1);
        PDDocument pdfDocument = PDDocument.load(newPdfInputStream);
        // 提取文本和坐标信息
        stripper.getText(pdfDocument);
        Coordinate coordinate = new Coordinate();
        coordinate.setX(stripper.xCoordinate);
        coordinate.setY(stripper.yCoordinate);
        return coordinate;
    }

    public static byte[] deleteRevisions(InputStream fis) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 创建一个XWPF文档对象
        XWPFDocument document = new XWPFDocument(fis);
        // 获取文档中的所有段落
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            // 遍历段落中的所有文本运行
            for (XWPFRun run : paragraph.getRuns()) {
                // 如果文本运行中包含批注，则删除该批注
                if (!run.getCTR().getCommentReferenceList().isEmpty()) {
                    run.getCTR().setCommentReferenceArray(new CTMarkup[]{});
                }
            }
        }
        // 清除文档中所有表格中的批注
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        for (XWPFRun run : paragraph.getRuns()) {
                            if (!run.getCTR().getCommentReferenceList().isEmpty()) {
                                run.getCTR().setCommentReferenceArray(new CTMarkup[]{});
                            }
                        }
                    }
                }
            }
        }
        // 将文档写入输出流
        document.write(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    @Data
    private static class Coordinate {
        private float y = 0;
        private float x = 0;
    }

    private static class MyPDFTextStripper extends PDFTextStripper {
        private StringBuilder currentText;

        private float yCoordinate = 0;
        private float xCoordinate = 0;

        public MyPDFTextStripper() throws IOException {
            super();
            currentText = new StringBuilder();
        }

        @Override
        protected void processTextPosition(TextPosition text) {
            // 获取文本内容
            String content = text.getUnicode();

            // 如果内容不为空，将其添加到当前文本字符串
            if (!content.isEmpty()) {
                currentText.append(content);
            }

            // 如果遇到分隔符（例如空格），表示当前段落结束
            if (text.getUnicode().equals(" ")) {
                // 处理当前文本字符串
                String paragraphText = currentText.toString();
                float x = text.getXDirAdj();
                float y = text.getYDirAdj();
                if (StringUtils.contains(paragraphText, "文件等级")) {
                    // 计算要补充的字数
                    yCoordinate = y;
                    xCoordinate = paragraphText.length() > 10 ? (float) (x - (paragraphText.length() - 4) * 1.2) : x + paragraphText.length() + 2;
                    System.out.println("paragraphText 文本: " + paragraphText);
                    System.out.println("paragraphText 文本字数: " + paragraphText.length());
                    System.out.println("X坐标0: " + x);
                    System.out.println("X坐标1: " + text.getX());
                    System.out.println("X坐标2: " + text.getXScale());
                    System.out.println("X坐标3: " + text.getEndX());
                }
                // 重置当前文本字符串
                currentText = new StringBuilder();
            }
        }
    }

    private final static DocumentFactory<CTDrawing> Factory = new DocumentFactory(TypeSystemHolder.typeSystem, "ctdrawing8d34type");

    /**
     * @param ctGraphicalObject 图片数据
     * @param deskFileName      图片描述
     * @param width             宽
     * @param height            高
     * @param leftOffset        水平偏移 left
     * @param topOffset         垂直偏移 top
     * @param behind            文字上方，文字下方
     * @return
     * @throws Exception
     */
    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
                                                String deskFileName, int width, int height,
                                                int leftOffset, int topOffset, boolean behind) {
        String anchorXML =
                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0)
                        + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">" + "<wp:simplePos x=\"0\" y=\"0\"/>"
                        + "<wp:positionH relativeFrom=\"column\">" + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
                        + "</wp:positionH>" + "<wp:positionV relativeFrom=\"page\">" + "<wp:posOffset>" + topOffset
                        + "</wp:posOffset>" + "</wp:positionV>" + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>" + "<wp:wrapNone/>"
                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
                        + "</wp:anchor>";

        CTDrawing drawing = null;
        try {

//            drawing = CTDrawing.Factory.parse(anchorXML);
            drawing = Factory.parse(anchorXML);
        } catch (XmlException e) {
            e.printStackTrace();
        }
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }
}
