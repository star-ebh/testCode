package com.example.data.purchase;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName orderController
 * @Description 采购订单控制器
 * @Author 星星泡面
 * @Date 2022/3/4 23:40
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderDataController {

    @PostMapping("/file")
    public void dataProcess(@RequestParam(name = "file") MultipartFile file) throws IOException {
        TimeInterval timer = DateUtil.timer();
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        ExcelUtil.readBySax(file.getInputStream(), 0, createRowHandler());
        Console.log("花费毫秒数", timer.interval());
        Console.log("花费分钟数", timer.intervalMinute());

    }

    /**
     * 序号索引
     */
    private int numberIndex;
    /**
     * 编码索引
     */
    private int codeIndex;
    /**
     * 名称索引
     */
    private int nameIndex;
    /**
     * 规格型号索引
     */
    private int specificationIndex;
    /**
     * 3月欠料索引
     */
    private int marchOweIndex;
    /**
     * 3月交付索引
     */
    private int marchDeliverIndex;
    /**
     * 4月欠料索引
     */
    private int aprilOweIndex;
    /**
     * 4月交付索引
     */
    private int aprilDeliverIndex;
    /**
     * 5月欠料索引
     */
    private int mayOweIndex;
    /**
     * 5月交付索引
     */
    private int mayDeliverIndex;
    /**
     * 6月欠料索引
     */
    private int juneOweIndex;
    /**
     * 6月交付索引
     */
    private int juneDeliverIndex;
    /**
     * 7月欠料索引
     */
    private int julyOweIndex;
    /**
     * 7月交付索引
     */
    private int julyDeliverIndex;
    /**
     * 8月欠料索引
     */
    private int augustOweIndex;
    /**
     * 8月交付索引
     */
    private int augustDeliverIndex;

    private final String TITLE_NUMBER ="序号";
    private final String TITLE_CODE ="编码";
    private final String TITLE_NAME ="名称";
    private final String TITLE_SPECIFICATION ="规格型号";
    private final String YEAR_BASE ="22";
    private final String OWE_BASE ="欠料";
    private final String DELIVER_BASE ="交付";
    private final String template = "%s年%s月%s";

    String a = String.format("%s7月%s", YEAR_BASE, OWE_BASE);

    private RowHandler createRowHandler() {
        return (sheetIndex, rowIndex, rowCells) -> {
            // 获取表头索引
            if (rowIndex == 0) {
                numberIndex = rowCells.indexOf(TITLE_NUMBER);
                codeIndex = rowCells.indexOf(TITLE_CODE);
                nameIndex = rowCells.indexOf(TITLE_NAME);
                specificationIndex = rowCells.indexOf(TITLE_SPECIFICATION);
                marchOweIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,3,OWE_BASE)) ;
                marchDeliverIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,3,DELIVER_BASE)) ;
                aprilOweIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,4,OWE_BASE)) ;
                aprilDeliverIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,4,DELIVER_BASE)) ;
                mayOweIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,5,OWE_BASE)) ;
                mayDeliverIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,5,DELIVER_BASE)) ;
                juneOweIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,6,OWE_BASE)) ;
                juneDeliverIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,6,DELIVER_BASE)) ;
                julyOweIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,7,OWE_BASE)) ;
                julyDeliverIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,7,DELIVER_BASE)) ;
                augustOweIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,8,OWE_BASE)) ;
                augustDeliverIndex =  rowCells.indexOf(String.format(template,YEAR_BASE,8,DELIVER_BASE)) ;
            }
            // 创建数据表
            // 创建List 拿到一条数据去采购订单的同步查询物料信息，进行计算填充对象，然后添加到List中
            Console.log(rowIndex, rowCells);
        };
    }
}
