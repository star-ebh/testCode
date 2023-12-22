package com.example.excel;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Test1 {
//    public static void main1(String[] args) throws Exception {
//        InputStream is = new FileInputStream("E://R-COP-02-TS-0220-001 M1P特殊特性清单-05版-20231102.xlsx");
//        InputStream is2 = new FileInputStream("E:\\yinzhang.png");
//        byte[] bytes = IOUtils.toByteArray(is2);
//        XSSFWorkbook wb = new XSSFWorkbook(is);
//        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
//            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
//            XSSFSheet sheet = wb.getSheetAt(i);
//            savePic1(sheet, wb, "E:\\yinzhang.png");
//        }
//        wb.write(new FileOutputStream("E://tttttttttttttttttt.xlsx"));
//    }

    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("E://R-COP-02-TS-0220-001 M1P特殊特性清单-05版-20231102.xlsx");
        InputStream is2 = new FileInputStream("E:\\yinzhang.png");
        byte[] bytes = IOUtils.toByteArray(is2);
        XSSFWorkbook wb = new XSSFWorkbook(is);
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            XSSFSheet sheet = wb.getSheetAt(i);
            pictureToSheet(sheet, sheet.getRow(2).getCell(2), pictureIdx);
        }
        wb.write(new FileOutputStream("E://tttttttttttttttttt.xlsx"));
    }

    //    private static void pictureToSheet(Sheet finalSheet, Cell cell, int pictureIdx) {
//        Drawing patriarch = finalSheet.createDrawingPatriarch();
//        CreationHelper helper = finalSheet.getWorkbook().getCreationHelper();
//        ClientAnchor anchor = helper.createClientAnchor();
//        // 图片插入坐标
//        anchor.setCol1(3);
//        anchor.setRow1(3);
//
//        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
//        // 指定我想要的长宽
//        double standardWidth = 112;
//        double standardHeight = 41;
//
//        // 计算单元格的长宽
//        double cellWidth = finalSheet.getColumnWidthInPixels(cell.getColumnIndex());
//        double cellHeight = cell.getRow().getHeightInPoints() / 72 * 96;
//
//        // 计算需要的长宽比例的系数
//        double a = standardWidth / cellWidth;
//        double b = standardHeight / cellHeight;
//
//        // 插入图片
//        Picture pict = patriarch.createPicture(anchor, pictureIdx);
//        pict.resize();
//        pict.resize(a, b);
//    }
    private static void pictureToSheet(Sheet finalSheet, Cell cell, int pictureIdx) {
        Drawing patriarch = finalSheet.createDrawingPatriarch();
        CreationHelper helper = finalSheet.getWorkbook().getCreationHelper();
        ClientAnchor anchor = helper.createClientAnchor();
        // 图片插入坐标
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_DONT_RESIZE);
        anchor.setCol1(8);
        anchor.setRow1(1);

        int pictureWidthInPixels = 120;
        int pictureHeightInPixels = 80;
        double pictureWidthInInches = pictureWidthInPixels / 96.0;
        double pictureHeightInInches = pictureHeightInPixels / 96.0;
//        anchor.setCol2(anchor.getCol1() + (int) (pictureWidthInInches * 256));
//        anchor.setRow2(anchor.getRow1() + (int) (pictureHeightInInches * 256));
        // 插入图片
        Picture picture = patriarch.createPicture(anchor, pictureIdx);
        Dimension imageDimension = picture.getImageDimension();
        System.out.println("宽："+imageDimension.getWidth());
        System.out.println("高："+imageDimension.getHeight());
        // 缩放图片到指定大小
        picture.resize(0.95,1);
        Dimension imageDimension1 = picture.getImageDimension();
        System.out.println("宽："+imageDimension1.getWidth());
        System.out.println("高："+imageDimension1.getHeight());
        System.out.println("========================================");
    }
}
