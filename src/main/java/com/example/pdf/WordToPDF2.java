package com.example.pdf;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;

import java.io.*;


@Slf4j
public class WordToPDF2 {

    public static void main(String[] args) {
//        "E:\\WordInsertPictures1.docx"
        String inputFilePath = "E:\\WordInsertPictures1.docx";
        String outputFilePath = "E:\\WordToPDF2.pdf";
        try {

//            InputStream inputStream = deleteRevisions(inputFilePath);
            InputStream inputStream = new FileInputStream(inputFilePath);
            OutputStream outputStream = new FileOutputStream(outputFilePath);
            XWPFDocument document = new XWPFDocument(inputStream);
            PdfOptions options = PdfOptions.create();
            // Convert .docx file to .pdf file
            PdfConverter.getInstance().convert(document, outputStream, options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ByteArrayInputStream deleteRevisions(String inputFilePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFilePath)) {
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
    }

}