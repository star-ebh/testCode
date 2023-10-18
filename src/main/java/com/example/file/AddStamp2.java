package com.example.file;//package com.example.file;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AddStamp2 {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("E:/R-SP-05-01-F05 04版 体系文件模板20230510.docx");
        OPCPackage open = OPCPackage.open(in);
        XWPFDocument doc = new XWPFDocument(open);
        createHeader(doc, "a公司", "aaaa");
    }

    public static void createHeader(XWPFDocument doc, String orgFullName, String logoFilePath) throws Exception {
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
        XWPFHeader header = headerFooterPolicy.createHeader(headerFooterPolicy.DEFAULT);
        XWPFParagraph paragraph = header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        paragraph.setBorderBottom(Borders.THICK);
        XWPFRun run = paragraph.createRun();
        if (StringUtils.isNotEmpty(logoFilePath)) {
            String imgFile = "E:\\yinzhang.png";
            File file = new File(imgFile);
            InputStream is = new FileInputStream(file);
            XWPFPicture picture = run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(80), Units.toEMU(45));
            String blipID = "";
            // 这段必须有，不然打开的logo图片不显示
            for (XWPFPictureData picturedata : header.getAllPackagePictures()) {
                blipID = header.getRelationId(picturedata);
                picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
            }
            run.addTab();
            is.close();
        }
        run.setText("你好");
        FileOutputStream fos = new FileOutputStream("E:/aaaab25.docx");
        doc.write(fos);
        fos.close();
    }

}
