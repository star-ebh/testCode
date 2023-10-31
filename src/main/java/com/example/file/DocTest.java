package com.example.file;


import cn.hutool.core.io.FileUtil;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;

import java.io.*;

public class DocTest {

    public static void main(String[] args) throws Exception {
        File inputWord = new File("E:\\RS-IC-01-0001 01版 内部控制管理制度.doc");
        File outputFile = new File("E:\\test1111111111111111111111111111111.docx");
        try (InputStream docxInputStream = new FileInputStream(inputWord);
             OutputStream outputStream = new FileOutputStream(outputFile)) {
            IConverter converter = LocalConverter.builder().build();
          converter.convert(docxInputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.DOCX).execute();
                converter.shutDown();
            System.out.println("转换成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        XWPFDocument document = new XWPFDocument(deleteRevisions(new FileInputStream("E:\\test1111111111111111111111111111111.docx")));

        XWPFHeader header = document.getHeaderList().get(0);
        XWPFParagraph paragraph = header.createParagraph();
        XWPFRun run = paragraph.createRun();
        // 添加浮动图片
        InputStream in = new FileInputStream("E:\\yinzhang.png");
        run.addPicture(in, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, "TEST", Units.toEMU(120), Units.toEMU(80));
        in.close();
        // 2. 获取到图片数据
        CTDrawing drawing = run.getCTR().getDrawingArray(0);
        CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();
        //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
        CTAnchor anchor = getAnchorWithGraphic(graphicalobject, "TEST1",
                Units.toEMU(120), Units.toEMU(80),//图片大小
                Units.toEMU(350), Units.toEMU(75), false);//相对当前段落位置 需要计算段落已有内容的左偏移
        drawing.setAnchorArray(new CTAnchor[]{anchor});//添加浮动属性
        drawing.removeInline(0);//删除行内属性
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        document.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        File newFile = new File("E:\\test22222222222222222222222222222222222.docx");
        FileUtil.writeBytes(byteArray, newFile);
    }

    public static ByteArrayInputStream deleteRevisions(InputStream fis) throws IOException {
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
        return new ByteArrayInputStream(byteArray);

    }

    public static CTAnchor getAnchorWithGraphic(CTGraphicalObject ctGraphicalObject,
                                                String deskFileName, int width, int height,
                                                int leftOffset, int topOffset, boolean behind) {
        // paragraph / page
        String anchorXML =
                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0) + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">"
                        + "<wp:simplePos x=\"0\" y=\"0\"/>"
                        + "<wp:positionH relativeFrom=\"column\">"
                        + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
                        + "</wp:positionH>"
                        + "<wp:positionV relativeFrom=\"page\">"
                        + "<wp:posOffset>" + topOffset + "</wp:posOffset>" +
                        "</wp:positionV>"
                        + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>"
                        + "<wp:wrapNone/>"
                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
                        + "</wp:anchor>";

        CTDrawing drawing = null;
        try {
            drawing = CTDrawing.Factory.parse(anchorXML);
        } catch (XmlException e) {
            e.printStackTrace();
        }
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }

}
