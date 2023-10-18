//package com.example.pdf;
//
//import fr.opensagres.poi.xwpf.converter.core.ImageManager;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.PicturesManager;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import org.apache.poi.hwpf.usermodel.PictureType;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.springframework.web.multipart.MultipartFile;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.*;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * poi WordToHtml工具类
// */
//@Slf4j
//public class WordToHtml {
//
//
//    //转换的方法
//    public File convert(MultipartFile file) {
//        //获得文件的名字
//        String filename = file.getOriginalFilename();
//        //获得文件的扩展名
//        String suffix = filename.substring(filename.lastIndexOf("."));
//        String newName = UUID.randomUUID().toString();
//        // TODO 需要保存在一个新的位置
//        // File =new File 表示目录的一个抽象,可以进一步用exists()和isDirectory()方法判断。
//        File convFile = new File("" + newName + suffix);
//        FileOutputStream fos = null;
//        try {
//            //创建文件
//            convFile.createNewFile();
//            //FileOutputStream 是输出流 将文件输出到磁盘或者数据库中
//            fos = new FileOutputStream(convFile);
//            fos.write(file.getBytes());
//        } catch (IOException ex) {
//            log.error("上传文件出错！", ex);
//            return null;
//        } finally {
//            IOUtils.closeQuietly(fos);
//        }
//
//        // 输入文件名的所在文件夹
//        // 加上反斜杠
//        String parentDirectory = convFile.getParent();
//        if (!parentDirectory.endsWith("\\")) {
//            parentDirectory = parentDirectory + "\\";
//        }
//
//        if (filename.endsWith(".docx")) {
//            return docxConvert(parentDirectory, convFile.getAbsolutePath(), newName);
//        } else if (filename.endsWith(".doc")) {
//            return docConvert(parentDirectory, convFile.getAbsolutePath(), newName);
//        } else {
//            log.error("不支持的文件格式！");
//            return null;
//        }
//    }
//
//
//    /**
//     * html 流文件 修改内容 width:595.3pt;  因为转换的HTML页面默认内容区域不是html自适应大小，内容位置不对
//     * @param parentDirectory html文件所在文件夹
//     * @param filename html旧文件地址
//     * @param newName html新文件地址
//     * @return
//     */
//    private static File htmlreplace(String parentDirectory, String filename, String newName) {
//        try {
////            读取生成的Html
//            FileInputStream inputStream = new FileInputStream(new File(parentDirectory + filename + ".html"));
//            InputStream inputStrem = readInputStrem(inputStream);
////            清空文件内容
//            clearInfoForFile(parentDirectory + filename + ".html");
//            // TODO: 2022/4/22 进行流输出Html文件 inputStrem
////            1、读取内容
//            byte[] buffer = new byte[inputStrem.available()];
//            inputStrem.read(buffer);
////            写入内容
//            OutputStream outStream = new FileOutputStream(new File(parentDirectory + newName + ".html"));
//            outStream.write(buffer);
//            return new File(parentDirectory + newName + ".html");
//        } catch (FileNotFoundException e) {
//            log.error("Html转换失败！",e);
//            return null;
//        } catch (IOException e) {
//            log.error("Html转换失败！",e);
//            return null;
//        }
//    }
//
//    /**
//     * 读取HTML 流文件，并查询当中的width:595.3pt;  / white-space:pre-wrap; 或类似符号直接替换为空格
//     *
//     * @param inputStream
//     * @return
//     */
//    private static InputStream readInputStrem(InputStream inputStream) {
////        匹配内容
//        String regEx_special = "width:595.3pt;";
//
//        String regEx_special2 = "white-space:pre-wrap;";
//
////        替换新内容
//        String replace = "white-space:pre-wrap;word-break:break-all;";
//        try {
//            //<1>创建字节数组输出流，用来输出读取到的内容
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            //<2>创建缓存大小
//            byte[] buffer = new byte[1024]; // 1KB
//            //每次读取到内容的长度
//            int len = -1;
//            //<3>开始读取输入流中的内容
//            while ((len = inputStream.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
//                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
//            }
//            //<4> 把字节数组转换为字符串
//            String content = baos.toString();
//            //<5>关闭输入流和输出流
////            inputStream.close();
//            baos.close();
////            log.info("读取的内容：{}", content);
////            判断HTML内容是否具有HTML的 width:595.3pt;
//            Pattern compile = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
//            Matcher matcher = compile.matcher(content);
//            String replaceAll = matcher.replaceAll("");
////            判断是否具有white-space:pre-wrap;
//            Pattern compile2 = Pattern.compile(regEx_special2, Pattern.CASE_INSENSITIVE);
//            Matcher matcher2 = compile2.matcher(replaceAll);
//            String replaceAll2 = matcher2.replaceAll(replace);
////            log.info("替换后的内容：{}", replaceAll2);
////            将字符串转化为输入流返回
//            InputStream stringStream = getStringStream(replaceAll2);
//            //<6>返回结果
//            return stringStream;
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("错误信息：{}", e.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * 将一个字符串转化为输入流
//     * @param sInputString 字符串
//     * @return
//     */
//    public static InputStream getStringStream(String sInputString) {
//        if (sInputString != null && !sInputString.trim().equals("")) {
//            try {
//                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
//                return tInputStringStream;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 清空文件内容
//     * @param fileName
//     */
//    public static void clearInfoForFile(String fileName) {
//        File file =new File(fileName);
//        try {
//            if(!file.exists()) {
//                file.createNewFile();
//            }
//            FileWriter fileWriter =new FileWriter(file);
//            fileWriter.write("");
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 转换.docx   当word文档字体大于5号字体时，会出现不规律排列文字换行(因为转换的HTML页面默认内容区域不是html原始区域)
//     * @param parentDirectory html文件所在文件夹 （主要用于图像的管理）
//     * @param filename word文件地址
//     * @param newName html文件地址
//     * @return
//     */
//    private static File docxConvert(String parentDirectory, String filename, String newName) {
//        try {
//            // 1) 加载word文档生成 XWPFDocument对象
//            XWPFDocument document = new XWPFDocument(new FileInputStream(filename));
//
////           设置存放图片地址
//            XHTMLOptions options = XHTMLOptions.create().setImageManager(new ImageManager(new File(parentDirectory), UUID.randomUUID().toString())).indent(4);
//            OutputStream out = new FileOutputStream(new File(parentDirectory + newName + ".html"));
////            自定义编码格式
//            OutputStreamWriter writer = new OutputStreamWriter(out,"GBK");
////            生成HTML
//            XHTMLConverter xhtmlConverter = (XHTMLConverter)XHTMLConverter.getInstance();
//            xhtmlConverter.convert(document, writer, options);
////            将生成的HTML进行内容匹配替换
//            File htmlreplace = htmlreplace(parentDirectory, newName, newName);
//            return htmlreplace;
////            return new File(parentDirectory + newName + ".html");
//        } catch (IOException ex) {
//            log.error("word转化出错！", ex);
//            return null;
//        }
//
//    }
//
//
//    /**
//     * 转换.doc
//     * @param parentDirectory html文件所在文件夹 （主要用于图像的管理）
//     * @param filename word文件地址
//     * @param newName html文件地址
//     * @return
//     */
//    private File docConvert(String parentDirectory, String filename, String newName) {
//        try {
//            HWPFDocument document = new HWPFDocument(new FileInputStream(filename));
//            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
//                            .newDocument());
//
//            // converter默认对图片不作处理，需要手动下载图片并嵌入到html中
//            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//                @Override
//                public String savePicture(byte[] bytes, PictureType pictureType, String s, float v, float v1) {
//                    String imageFilename = parentDirectory + "";
//                    String identity = UUID.randomUUID().toString();
//                    File imageFile = new File(imageFilename, identity + s);
//                    imageFile.getParentFile().mkdirs();
//                    InputStream in = null;
//                    FileOutputStream out = null;
//
//                    try {
//                        in = new ByteArrayInputStream(bytes);
//                        out = new FileOutputStream(imageFile);
//                        IOUtils.copy(in, out);
//
//                    } catch (IOException ex) {
//                        log.error("word转化出错！", ex);
//                    } finally {
//                        if (in != null) {
//                            IOUtils.closeQuietly(in);
//                        }
//
//                        if (out != null) {
//                            IOUtils.closeQuietly(out);
//                        }
//
//                    }
//                    return imageFile.getName();
//                }
//            });
//
//            wordToHtmlConverter.processDocument(document);
//            Document htmlDocument = wordToHtmlConverter.getDocument();
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            DOMSource domSource = new DOMSource(htmlDocument);
//            StreamResult streamResult = new StreamResult(out);
//
////            设置转换属性
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer serializer = tf.newTransformer();
//            serializer.setOutputProperty(OutputKeys.ENCODING, "GBK");
//            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//            serializer.setOutputProperty(OutputKeys.METHOD, "html");
//            serializer.transform(domSource, streamResult);
//            out.close();
//
//            String result = new String(out.toByteArray());
//            FileWriter writer = new FileWriter(parentDirectory + newName + ".html");
//            writer.write(result);
//            writer.close();
//        } catch (IOException | TransformerException | ParserConfigurationException ex) {
//            log.error("word转化出错！", ex);
//        }
//        return new File(parentDirectory + newName + ".html");
//    }
//
//    /**
//     * 将上传的Word文档转化成HTML字符串
//     *
//     * @param file
//     * @return
//     */
//    public String convertToHtml(MultipartFile file) {
//        String wordContent = "";
//        // 将Word文件转换为html
//        File file2 = convert(file);
//        // 读取html文件
//        if (file2 != null) {
//            return "文件转换成功";
//        }
//        return "文件转换失败";
//    }
//
//    /**
//     * wordToHtml
//     * @param wordFilePath word文件路径
//     * @param htmlFilePath html文件路径
//     * @throws IOException
//     * @throws ParserConfigurationException
//     * @throws TransformerException
//     */
//    public static File wordToHtml(String wordFilePath,String htmlFilePath) {
////        提取出word文档名称和后缀
//        String filename = wordFilePath.substring(wordFilePath.lastIndexOf("/")+1);
////        提取出html文件存放路径和文件名称
//        String newName = htmlFilePath.substring(htmlFilePath.lastIndexOf("/")+1,htmlFilePath.lastIndexOf("."));
//        File convFile = new File(htmlFilePath);
//        // 输入文件名的所在文件夹
//        // 加上反斜杠
//        String parentDirectory = convFile.getParent();
//        if (!parentDirectory.endsWith("\\")) {
//            parentDirectory = parentDirectory + "\\";
//        }
//
//        if (filename.endsWith(".docx")) {
//            return new WordToHtml().docxConvert(parentDirectory, wordFilePath, newName);
//        } else if (filename.endsWith(".doc")) {
//            return new WordToHtml().docConvert(parentDirectory, wordFilePath, newName);
//        } else {
//            log.error("不支持的文件格式！");
//            return null;
//        }
//
//    }
//    public static void main(String[] args) {
//        long timeMillis = System.currentTimeMillis();
//        log.info("开始转换！");
//        String wordFilePath = "E:\\WordInsertPictures1.docx";
//        String htmlFilePath = "output";
//        File file = docxConvert("E:\\", wordFilePath, htmlFilePath);
//        // 读取html文件
//        if (file != null) {
//            log.info("文件存放路径：{}",file.getPath());
//            log.info("转换结束！用时：{}ms",System.currentTimeMillis()-timeMillis);
//            return;
//        }
//        log.error("文件转换失败！");
//    }
//}