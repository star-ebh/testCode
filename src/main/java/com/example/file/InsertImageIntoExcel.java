package com.example.file;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InsertImageIntoExcel {
    public static void main(String[] args) throws Exception {
        // 读取输入图像数据
        String imagePath = "E:\\yinzhang.png";
        FileInputStream imageStream = new FileInputStream(imagePath);

        String excelPath = "Q:\\Downloads\\受控版 R-COP-05-FC-0214 0214振镜 过程流程图 08版.xlsx";
        FileInputStream excelStream = new FileInputStream(excelPath);

        byte[] imageData = new byte[imageStream.available()];
        imageStream.read(imageData);
        imageStream.close();

        byte[] excelData = new byte[excelStream.available()];
        excelStream.read(excelData);
        excelStream.close();

        // 指定输出Excel文件路径
        String outputPath = "Q:\\Downloads\\生成后.xlsx";
        File outputFile = new File(outputPath);
        // 创建Excel工作簿并添加图像
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(excelStream);
        XSSFDrawing drawing = workbook.createSheet().createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, /*col*/0, /*row*/0, /*col*/1, /*row*/1); // 指定图像位置和大小
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_DONT_RESIZE);
        drawing.createPicture(anchor, workbook.addPicture(imageData, Workbook.PICTURE_TYPE_JPEG)); // 指定图像类型
        FileOutputStream out = new FileOutputStream(outputFile);
        workbook.write(out);
        out.close();
        workbook.close();
    }
}
