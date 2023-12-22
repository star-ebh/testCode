//package com.example.file;
//
//import com.alibaba.fastjson.JSONObject;
//import com.aspose.words.SaveFormat;
//import com.deepoove.poi.XWPFTemplate;
//import com.deepoove.poi.config.Configure;
//import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
//import com.google.common.collect.Maps;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.usermodel.Document;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFPictureData;
//import org.apache.xmlbeans.XmlOptions;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.*;
//
//public class Test456 {
//    public static void main(String[] args) throws Exception {
//        JSONObject masterData = new JSONObject();
//        masterData.put("APPLICATION_USER_NAME", "贾益帆");
//        masterData.put("APPLICATION_DEPARTMENT", "测试部");
//        masterData.put("APPLICATION_USER_NO", "123456");
//        masterData.put("DOCNO", "FK-xxxxxxxx1111");
//        masterData.put("THEME_DESCRIBES", "我是测试主题");
//        masterData.put("INTERNAL_COMPANY_BODY", "测试内部公司主体");
//        masterData.put("PAYMENT_TYPE", "我是付款类型");
//        masterData.put("ORDER_PAYMENT_TYPE", "我是订单付款类型");
//        masterData.put("EXPECT_PAYMENT_DATE", "2023-11-02");
//        masterData.put("ASSOCIATED_RELATIONSHIP_TYPE", "我是关联往来类型");
//        masterData.put("BUSINESS_CATEGORY", "我是业务分类类型");
//        List<JSONObject> details = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            JSONObject good = new JSONObject();
//            good.put("INDEX", i + 1);
//            good.put("DOCUMENT_TYPE", "测试单据");
//            good.put("BUSINESS_DATE", "2023-11-02");
//            good.put("DOCUMENT_CURRENCY", "人民币");
//            good.put("PREPAID_AMOUNT", "99999");
//            good.put("REMAINING_UNPAID_AMOUNT", "99999");
//            good.put("PAYMENT_CURRENCY", "人民币");
//            good.put("PAYMENT_AMOUNT", "10000");
//            good.put("NOTE", "你没事吧");
//            details.add(good);
//        }
//
//
//        Map<String, Object> data = Maps.newHashMap();
//        data.put("DETAILS", details);
//        LoopRowTableRenderPolicy hackLoopTableRenderPolicy = new LoopRowTableRenderPolicy();
//        Configure config = Configure.builder().bind("DETAILS", hackLoopTableRenderPolicy).build();
//        XWPFTemplate template = XWPFTemplate.compile("E:\\付款导出模板\\付款流程.docx", config).render(data).render(masterData);
//        template.writeToFile("E:\\付款导出模板\\output2.docx");
//        appendDocx(new File("E:\\付款导出模板\\合并文件.docx"), Arrays.asList(new File("E:\\付款导出模板\\output1.docx"), new File("E:\\付款导出模板\\output2.docx")));
//        com.aspose.words.Document doc = new com.aspose.words.Document("E:\\付款导出模板\\合并文件.docx");
//        doc.save("E:\\付款导出模板\\合并文件.pdf", SaveFormat.PDF);
//
//    }
//
//    /**
//     * 把多个docx文件合并成一个
//     *
//     * @param outfile    输出文件
//     * @param targetFile 目标文件
//     */
//    public static void appendDocx(File outfile, List<File> targetFile) {
//        try {
//            OutputStream dest = new FileOutputStream(outfile);
//            ArrayList<XWPFDocument> documentList = new ArrayList<>();
//            XWPFDocument doc = null;
//            for (int i = 0; i < targetFile.size(); i++) {
//                FileInputStream in = new FileInputStream(targetFile.get(i).getPath());
//                OPCPackage open = OPCPackage.open(in);
//                XWPFDocument document = new XWPFDocument(open);
//                documentList.add(document);
//            }
//            for (int i = 0; i < documentList.size(); i++) {
//                doc = documentList.get(0);
//                if (i != 0) {
//                    //解决word合并完后，所有表格都紧紧挨在一起，没有分页。加上了分页符可解决
////                    documentList.get(i).createParagraph().setPageBreak(true);
//                    doc.createParagraph().setPageBreak(true);
//                    appendBody(doc, documentList.get(i));
//                }
//            }
//            // 分页
//            doc.write(dest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void appendBody(XWPFDocument src, XWPFDocument append) throws Exception {
//        CTBody src1Body = src.getDocument().getBody();
//        CTBody src2Body = append.getDocument().getBody();
//
//        List<XWPFPictureData> allPictures = append.getAllPictures();
//        // 记录图片合并前及合并后的ID
//        Map<String, String> map = new HashMap<>();
//        for (XWPFPictureData picture : allPictures) {
//            String before = append.getRelationId(picture);
//            //将原文档中的图片加入到目标文档中
//            String after = src.addPictureData(picture.getData(), Document.PICTURE_TYPE_PNG);
//            map.put(before, after);
//        }
//
//        appendBody(src1Body, src2Body, map);
//
//    }
//
//    private static void appendBody(CTBody src, CTBody append, Map<String, String> map) throws Exception {
//        XmlOptions optionsOuter = new XmlOptions();
//        optionsOuter.setSaveOuter();
//        String appendString = append.xmlText(optionsOuter);
//
//        String srcString = src.xmlText();
//        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
//        String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
//        String sufix = srcString.substring(srcString.lastIndexOf("<"));
//        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
//        //下面这部分可以去掉，我加上的原因是合并的时候，有时候出现打不开的情况，对照document.xml将某些标签去掉就可以正常打开了
//        addPart = addPart.replaceAll("w14:paraId=\"[A-Za-z0-9]{1,10}\"", "");
//        addPart = addPart.replaceAll("w14:textId=\"[A-Za-z0-9]{1,10}\"", "");
//        addPart = addPart.replaceAll("w:rsidP=\"[A-Za-z0-9]{1,10}\"", "");
//        addPart = addPart.replaceAll("w:rsidRPr=\"[A-Za-z0-9]{1,10}\"", "");
//        addPart = addPart.replace("<w:headerReference r:id=\"rId8\" w:type=\"default\"/>", "");
//        addPart = addPart.replace("<w:footerReference r:id=\"rId9\" w:type=\"default\"/>", "");
//        addPart = addPart.replace("xsi:nil=\"true\"", "");
//
//        if (map != null && !map.isEmpty()) {
//            //对xml字符串中图片ID进行替换
//            for (Map.Entry<String, String> set : map.entrySet()) {
//                addPart = addPart.replace(set.getKey(), set.getValue());
//            }
//        }
//        //将两个文档的xml内容进行拼接
//        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
//
//        src.set(makeBody);
//    }
//}
