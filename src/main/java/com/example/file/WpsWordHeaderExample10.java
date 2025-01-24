//package com.example.file;
//
//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.ComThread;
//import com.jacob.com.Dispatch;
//import com.jacob.com.Variant;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.pdfbox.text.TextPosition;
//import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
//import org.apache.xmlbeans.XmlException;
//import org.apache.xmlbeans.impl.schema.DocumentFactory;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
//import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//
//@Slf4j
//public class WpsWordHeaderExample10 {
//    private static final int wdFormatPDF = 17;
//    private static final int xlTypePDF = 0;
//    private static final int ppSaveAsPDF = 32;
//
//    private String office_path = "Q:\\Downloads\\";
//    private String pdf_path = "Q:\\Downloads\\";
//
//    public static void main(String[] args) throws Exception {
//        String fileName = "Q:\\Downloads\\wps文件.docx";
//        String newFileName = "Q:\\Downloads\\wps文件-去除标记后.docx";
//        String pdfName = "Q:\\Downloads\\wps文件转换pdf-new.pdf";
//        WpsWordHeaderExample10 wpsWordHeaderExample10 = new WpsWordHeaderExample10();
//        wpsWordHeaderExample10.office2pdf(fileName);
////        byte[] bytes = IoUtil.readBytes(inputStream);
////        IoUtil.write(new FileOutputStream(pdfName), true, bytes);
//    }
//
//    public boolean office2pdf(String inputFileName) {
//
//        String officeUserPath = inputFileName;
//        inputFileName = inputFileName.substring(0, inputFileName.lastIndexOf("."));
//        String pdfUserPath = String.format(pdf_path + "%s", inputFileName + ".pdf");
//
//        int time = convert2PDF(officeUserPath, pdfUserPath);
//        boolean result = false;
//        if (time == -4) {
//            log.info("转化失败，未知错误...");
//        } else if (time == -3) {
//            result = true;
//            log.info("原文件就是PDF文件,无需转化...");
//        } else if (time == -2) {
//            log.info("转化失败，文件不存在...");
//        } else if (time == -1) {
//            log.info("转化失败，请重新尝试...");
//        } else if (time < -4) {
//            log.info("转化失败，请重新尝试...");
//        } else {
//            result = true;
//            log.info("转化成功，用时：  " + time + "s...");
//        }
//        return result;
//    }
//
//    public static String getFileSufix(String fileName) {
//        int splitIndex = fileName.lastIndexOf(".");
//        return fileName.substring(splitIndex + 1);
//    }
//
//    private int word2PDF(String inputFile, String pdfFile) {
//        try {
//            // 打开Word应用程序
//            ActiveXComponent app = new ActiveXComponent("KWPS.Application");
//            log.info("开始转化Word为PDF...");
//            long date = new Date().getTime();
//            // 设置Word不可见
//            app.setProperty("Visible", new Variant(false));
//            // 禁用宏
//            app.setProperty("AutomationSecurity", new Variant(3));
//            // 获得Word中所有打开的文档，返回documents对象
//            Dispatch docs = app.getProperty("Documents").toDispatch();
//            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
//            Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
//            /***
//             *
//             * 调用Document对象的SaveAs方法，将文档保存为pdf格式
//             *
//             * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF word保存为pdf格式宏，值为17 )
//             *
//             */
//            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17
//            // 关闭文档
//            long date2 = new Date().getTime();
//            int time = (int) ((date2 - date) / 1000);
//
//            Dispatch.call(doc, "Close", false);
//            // 关闭Word应用程序
//            app.invoke("Quit", 0);
//            return time;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//
//    }
//
//    /***
//     *
//     * Excel转化成PDF
//     *
//     * @param inputFile
//     * @param pdfFile
//     * @return
//     */
//    private int Ex2PDF(String inputFile, String pdfFile) {
//        try {
//
//            ComThread.InitSTA(true);
//            ActiveXComponent ax = new ActiveXComponent("KET.Application");
//            log.info("开始转化Excel为PDF...");
//            long date = new Date().getTime();
//            ax.setProperty("Visible", false);
//            ax.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
//            Dispatch excels = ax.getProperty("Workbooks").toDispatch();
//
//            Dispatch excel = Dispatch
//                    .invoke(excels, "Open", Dispatch.Method,
//                            new Object[]{inputFile, new Variant(false), new Variant(false)}, new int[9])
//                    .toDispatch();
//            // 转换格式
//            Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method, new Object[]{new Variant(0), // PDF格式=0
//                    pdfFile, new Variant(xlTypePDF) // 0=标准 (生成的PDF图片不会变模糊) 1=最小文件
//                    // (生成的PDF图片糊的一塌糊涂)
//            }, new int[1]);
//
//            // 这里放弃使用SaveAs
//            /*
//             * Dispatch.invoke(excel,"SaveAs",Dispatch.Method,new Object[]{ outFile, new
//             * Variant(57), new Variant(false), new Variant(57), new Variant(57), new
//             * Variant(false), new Variant(true), new Variant(57), new Variant(true), new
//             * Variant(true), new Variant(true) },new int[1]);
//             */
//            long date2 = new Date().getTime();
//            int time = (int) ((date2 - date) / 1000);
//            Dispatch.call(excel, "Close", new Variant(false));
//
//            if (ax != null) {
//                ax.invoke("Quit", new Variant[]{});
//                ax = null;
//            }
//            ComThread.Release();
//            return time;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//    /***
//     * ppt转化成PDF
//     *
//     * @param inputFile
//     * @param pdfFile
//     * @return
//     */
//    private int ppt2PDF(String inputFile, String pdfFile) {
//        log.info("开始转化PPT为PDF...");
//        try {
//            ComThread.InitSTA(true);
//            ActiveXComponent app = new ActiveXComponent("KWPP.Application");
////            app.setProperty("Visible", false);
//            long date = new Date().getTime();
//            Dispatch ppts = app.getProperty("Presentations").toDispatch();
//            Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true, // ReadOnly
//                    // false, // Untitled指定文件是否有标题
//                    false// WithWindow指定文件是否可见
//            ).toDispatch();
//            Dispatch.invoke(ppt, "SaveAs", Dispatch.Method, new Object[]{pdfFile, new Variant(ppSaveAsPDF)},
//                    new int[1]);
//            log.info("PPT");
//            Dispatch.call(ppt, "Close");
//            long date2 = new Date().getTime();
//            int time = (int) ((date2 - date) / 1000);
//            app.invoke("Quit");
//            return time;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//
//    // 删除多余的页，并转换为PDF
//    public static void interceptPPT(String inputFile, String pdfFile) {
//        ActiveXComponent app = null;
//        try {
//            ComThread.InitSTA(true);
//            app = new ActiveXComponent("KWPP.Application");
//            ActiveXComponent presentations = app.getPropertyAsComponent("Presentations");
//            ActiveXComponent presentation = presentations.invokeGetComponent("Open", new Variant(inputFile),
//                    new Variant(false));
//            int count = Dispatch.get(presentations, "Count").getInt();
//            System.out.println("打开文档数:" + count);
//            ActiveXComponent slides = presentation.getPropertyAsComponent("Slides");
//            int slidePages = Dispatch.get(slides, "Count").getInt();
//            System.out.println("ppt幻灯片总页数:" + slidePages);
//
//            // 总页数的20%取整+1 最多不超过5页
//            int target = (int) (slidePages * 0.5) + 1 > 5 ? 5 : (int) (slidePages * 0.5) + 1;
//            // 删除指定页数
//            while (slidePages > target) {
//                // 选中指定页幻灯片
//                Dispatch slide = Dispatch.call(presentation, "Slides", slidePages).toDispatch();
//                Dispatch.call(slide, "Select");
//                Dispatch.call(slide, "Delete");
//                slidePages--;
//                System.out.println("当前ppt总页数:" + slidePages);
//            }
//            Dispatch.invoke(presentation, "SaveAs", Dispatch.Method, new Object[]{pdfFile, new Variant(32)},
//                    new int[1]);
//            Dispatch.call(presentation, "Save");
//            Dispatch.call(presentation, "Close");
//            presentation = null;
//            app.invoke("Quit");
//            app = null;
//            ComThread.Release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        }
//    }
//
//    private int convert2PDF(String inputFile, String pdfFile) {
//        String kind = getFileSufix(inputFile);
//        File file = new File(inputFile);
//        if (!file.exists()) {
//            log.info("文件不存在");
//            return -2;
//        }
//        if (kind.equals("pdf")) {
//            log.info("原文件就是PDF文件");
//            return -3;
//        }
//        if (kind.equals("doc") || kind.equals("docx") || kind.equals("txt")) {
//            return word2PDF(inputFile, pdfFile);
//        } else if (kind.equals("ppt") || kind.equals("pptx")) {
//            return ppt2PDF(inputFile, pdfFile);
//        } else if (kind.equals("xls") || kind.equals("xlsx")) {
//            return Ex2PDF(inputFile, pdfFile);
//        } else {
//            return -4;
//        }
//    }
//
//    @Data
//    private static class Coordinate {
//        private float y = 0;
//        private float x = 0;
//    }
//
//    private static class MyPDFTextStripper extends PDFTextStripper {
//        private StringBuilder currentText;
//
//        private float yCoordinate = 0;
//        private float xCoordinate = 0;
//
//        public MyPDFTextStripper() throws IOException {
//            super();
//            currentText = new StringBuilder();
//        }
//
//        @Override
//        protected void processTextPosition(TextPosition text) {
//            // 获取文本内容
//            String content = text.getUnicode();
//
//            // 如果内容不为空，将其添加到当前文本字符串
//            if (!content.isEmpty()) {
//                currentText.append(content);
//            }
//
//            // 如果遇到分隔符（例如空格），表示当前段落结束
//            if (text.getUnicode().equals(" ")) {
//                // 处理当前文本字符串
//                String paragraphText = currentText.toString();
//                float x = text.getXDirAdj();
//                float y = text.getYDirAdj();
//                if (StringUtils.contains(paragraphText, "文件等级")) {
//                    // 计算要补充的字数
//                    yCoordinate = y;
//                    xCoordinate = paragraphText.length() > 10 ? (float) (x - (paragraphText.length() - 4) * 1.2) : x + paragraphText.length() + 2;
//                    System.out.println("paragraphText 文本: " + paragraphText);
//                    System.out.println("paragraphText 文本字数: " + paragraphText.length());
//                    System.out.println("X坐标0: " + x);
//                    System.out.println("X坐标1: " + text.getX());
//                    System.out.println("X坐标2: " + text.getXScale());
//                    System.out.println("X坐标3: " + text.getEndX());
//                }
//                // 重置当前文本字符串
//                currentText = new StringBuilder();
//            }
//        }
//    }
//
//    private final static DocumentFactory<CTDrawing> Factory = new DocumentFactory(TypeSystemHolder.typeSystem, "ctdrawing8d34type");
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
//        String anchorXML =
//                "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
//                        + "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0)
//                        + "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">" + "<wp:simplePos x=\"0\" y=\"0\"/>"
//                        + "<wp:positionH relativeFrom=\"column\">" + "<wp:posOffset>" + leftOffset + "</wp:posOffset>"
//                        + "</wp:positionH>" + "<wp:positionV relativeFrom=\"page\">" + "<wp:posOffset>" + topOffset
//                        + "</wp:posOffset>" + "</wp:positionV>" + "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
//                        + "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/>" + "<wp:wrapNone/>"
//                        + "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + deskFileName + "\"/><wp:cNvGraphicFramePr/>"
//                        + "</wp:anchor>";
//
//        CTDrawing drawing = null;
//        try {
//
////            drawing = CTDrawing.Factory.parse(anchorXML);
//            drawing = Factory.parse(anchorXML);
//        } catch (XmlException e) {
//            e.printStackTrace();
//        }
//        CTAnchor anchor = drawing.getAnchorArray(0);
//        anchor.setGraphic(ctGraphicalObject);
//        return anchor;
//    }
//}
