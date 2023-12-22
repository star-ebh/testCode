package com.example.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

public class ImportExcelUtils {
    /**
     * 创建WorkBook对象
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static final Workbook createWorkbook(String filePath) throws IOException {
        if (filePath.trim().toLowerCase().endsWith("xls")) {
            return new XSSFWorkbook(new FileInputStream(filePath));
        } else if (filePath.trim().toLowerCase().endsWith("xlsx")) {
            return new XSSFWorkbook(new FileInputStream(filePath));
        } else {
            throw new IllegalArgumentException("不是有效的excel文件格式");
        }
    }

    /**
     * 创建WorkBook对象
     *
     * @param uploadFile
     * @return
     * @throws IOException
     */
    public static final Workbook createWorkbook(MultipartFile uploadFile) throws IOException {
        return new XSSFWorkbook(uploadFile.getInputStream());
    }

    /**
     * 获取Sheet页面(按名称)
     *
     * @param wb
     * @param sheetName
     * @return
     */
    public static final Sheet getSheet(Workbook wb, String sheetName) {
        return wb.getSheet(sheetName);
    }

    /**
     * 获取Sheet页面(按页标)
     *
     * @param wb
     * @param index
     * @return
     */
    public static final Sheet getSheet(Workbook wb, int index) {
        return wb.getSheetAt(index);
    }

}
