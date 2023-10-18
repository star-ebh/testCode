package com.example.pdf;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;

import java.io.*;

@Slf4j
public class WordToPDF {
    private static final IConverter CONVERTER = LocalConverter.builder().build();

    public static void main(String[] args) {
//        "E:\\WordInsertPictures1.docx"
        String inputFilePath = "E:\\WordInsertPictures1.docx";
        String outputFilePath = "E:\\output111111111111111.pdf";
        try {

            InputStream docxInputStream = deleteRevisions(inputFilePath);
            OutputStream outputStream = new FileOutputStream(outputFilePath);
            IConverter converter = LocalConverter.builder().baseFolder(new File("Q:\\Downloads\\test")).build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            outputStream.close();
            System.out.println("success");
            // 关闭转换器
            converter.shutDown();
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

    /**
     * 通过documents4j 实现word转pdf
     *
     * @param sourcePath 源文件地址 如 /root/example.doc
     * @param targetPath 目标文件地址 如 /root/example.pdf
     */
    public static void documents4jWordToPdf(String sourcePath, String targetPath) {
        File inputWord = new File(sourcePath);
        File outputFile = new File(targetPath);
        try {
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);

            CONVERTER.convert(docxInputStream)
                    .as(DocumentType.DOCX)
                    .to(outputStream)
                    .as(DocumentType.PDF).execute();
            outputStream.close();
            docxInputStream.close();

            log.info("转换完毕 targetPath = {}", outputFile.getAbsolutePath());
            CONVERTER.shutDown();
        } catch (Exception e) {
            log.error("[documents4J] word转pdf失败:{}", e.toString());
        }
    }
}