package com.example.file;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

import java.io.FileInputStream;
import java.io.IOException;

public class WordHeaderExample8 {
    public static void main(String[] args) throws Exception {
        FileInputStream pdfFile = new FileInputStream("E:\\备用金管理办法Pictures1.pdf"); // 之前转换的PDF文件
        PDDocument pdfDocument = PDDocument.load(pdfFile);
        // 创建一个自定义的文本提取器，以获取坐标信息
        MyPDFTextStripper stripper = new MyPDFTextStripper();
        stripper.setStartPage(0);
        stripper.setEndPage(1);

        // 提取文本和坐标信息
        String text = stripper.getText(pdfDocument);
        System.out.println("text=>" + text);
        System.out.println("yCoordinate=>" + stripper.yCoordinate);
        System.out.println("xCoordinate=>" + stripper.xCoordinate);
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

                System.out.println("段落内容: " + paragraphText);
                System.out.println("X坐标: " + x);
                System.out.println("Y坐标: " + y);
                if (StringUtils.contains(paragraphText, "等级")) {
                    yCoordinate = y;
                    xCoordinate = x;
                }
                // 在这里，你可以根据Y坐标信息估算段落距禖页面顶部的高度

                // 重置当前文本字符串
                currentText = new StringBuilder();
            }
        }

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
