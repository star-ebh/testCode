package com.example.file;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class WordHeaderExample {
    public static void main(String[] args) throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream("E:\\备用金管理办法.docx"));
        XWPFHeader header = document.getHeaderList().get(0);
        XWPFParagraph paragraph = header.createParagraph();
        XWPFRun run = paragraph.createRun();
        // 设置图片
        // 添加浮动图片
        run = paragraph.createRun();
        InputStream in = new FileInputStream("E:\\yinzhang.png");
        run.addPicture(in, Document.PICTURE_TYPE_PNG, "TEST", Units.toEMU(120), Units.toEMU(80));
        in.close();
        // 2. 获取到图片数据
        CTDrawing drawing = run.getCTR().getDrawingArray(0);
        CTGraphicalObject graphicalobject = drawing.getInlineArray(0).getGraphic();

        //拿到新插入的图片替换添加CTAnchor 设置浮动属性 删除inline属性
        CTAnchor anchor = getAnchorWithGraphic(graphicalobject, "TEST1", Units.toEMU(120), Units.toEMU(80),// 图片大小
                // 相对当前段落位置 需要计算段落已有内容的左偏移
                Units.toEMU(350), Units.toEMU(75), false);
        drawing.setAnchorArray(new CTAnchor[]{anchor});//添加浮动属性
        drawing.removeInline(0);//删除行内属性
        document.write(new FileOutputStream("E:\\备用金管理办法Pictures1.docx"));
        document.close();

        IConverter converter = LocalConverter.builder().build();
        converter.convert(new FileInputStream("E:\\备用金管理办法Pictures1.docx")).as(DocumentType.DOCX).to(new File("E:\\备用金管理办法Pictures1.pdf")).as(DocumentType.PDF).execute();
        converter.shutDown();
    }

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
            drawing = CTDrawing.Factory.parse(anchorXML);
        } catch (XmlException e) {
            e.printStackTrace();
        }
        CTAnchor anchor = drawing.getAnchorArray(0);
        anchor.setGraphic(ctGraphicalObject);
        return anchor;
    }
}
