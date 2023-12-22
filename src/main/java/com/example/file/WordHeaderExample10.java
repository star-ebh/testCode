package com.example.file;

import cn.hutool.core.io.IoUtil;
import cn.robosense.SdkClient;
import cn.robosense.core.enums.EdiActiveEnum;
import cn.robosense.service.file.v1.model.WordToPdfReq;
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
import java.util.List;
import java.util.Objects;

public class WordHeaderExample10 {
    public static void main(String[] args) throws Exception {
//        String fileName = "E:\\RS-FIN-01-0001 02版 财务管理制度.docx";
//        String newFileName = "E:\\RS-FIN-01-0001 02版 财务管理制度1111111111111111111.docx";
//        String pdfName = "E:\\RS-FIN-01-0001 02版 财务管理制度.docx.pdf";
        IConverter converter = LocalConverter.builder().build();

        String fileName = "Q:\\Downloads\\原文件.docx";
        String newFileName = "Q:\\Downloads\\RS-AD-02-0003 01版 国内员工差旅管理规定-new.docx";
        String pdfName = "Q:\\Downloads\\window本地-new.pdf";
        byte[] byteArray = deleteRevisions(new FileInputStream(fileName));
        Coordinate position = getPosition(converter, byteArray);
        System.out.println("Y轴=>" + position.getY());
        System.out.println("X轴=>" + position.getX());
        ByteArrayInputStream wordInputStream = new ByteArrayInputStream(byteArray);

        XWPFDocument document = new XWPFDocument(wordInputStream);
        XWPFHeader header = document.getHeaderList().get(0);

        XWPFParagraph paragraph = header.createParagraph();
        XWPFRun run = paragraph.createRun();
        // 设置图片
        // 添加浮动图片
        InputStream in = new FileInputStream("E:\\yinzhang.png");
        run.addPicture(in, Document.PICTURE_TYPE_PNG, "TEST", Units.toEMU(120), Units.toEMU(80));
        in.close();
        // 2. 获取到图片数据
        CTDrawing drawing = run.getCTR().getDrawingArray(0);
        CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();

        //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
        CTAnchor anchor = getAnchorWithGraphic(graphicalobject, "TEST1", Units.toEMU(120), Units.toEMU(80),// 图片大小
                // 相对当前段落位置 需要计算段落已有内容的左偏移
                Units.toEMU(position.getX() - 120), Units.toEMU(position.getY() - 75), false);
        drawing.setAnchorArray(new CTAnchor[]{anchor});//添加浮动属性
        drawing.removeInline(0);//删除行内属性
        for (int i = header.getParagraphs().size() - 1; i >= 0; i--) {
            XWPFParagraph itemParagraph = header.getParagraphs().get(i);
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
        document.write(new FileOutputStream(newFileName));
        document.close();
//        SdkClient client = SdkClient.newBuilder("mqRMb0Jk", "8b645b0593bb54e643c8d54cbef8083d", "3FFE7F96E879909109C857DE7D8CA00B21C10DC56F0651A47C9DF3E9B7475A5D", "0474A35F34C05C378C46F663D027E5F8AE097A756BE1AE95C1CA7A8104D3BCC29D9388F6140DCC3F63FF257B25B726C75D13919A792EB2EE4D0F85DD98403B7AF8", "KaKOu26cUMcvmEJr", EdiActiveEnum.TEST).build();
//        InputStream inputStream = client
//                .fileService()
//                .word()
//                .wordToPdf(WordToPdfReq.builder().fileName(pdfName).build(), new FileInputStream(newFileName));
        converter.convert(new FileInputStream(newFileName)).as(DocumentType.DOCX).to(new File(pdfName)).as(DocumentType.PDF).execute();
        converter.shutDown();
//        byte[] bytes = IoUtil.readBytes(inputStream);
//        IoUtil.write(new FileOutputStream(pdfName), true, bytes);
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
