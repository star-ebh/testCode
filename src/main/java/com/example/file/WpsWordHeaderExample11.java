package com.example.file;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class WpsWordHeaderExample11 {

    public static void main(String[] args) throws Exception {
        String fileName = "Q:\\Downloads\\wps文件.docx";
        String newFileName = "Q:\\Downloads\\wps文件-去除标记后.docx";
        String pdfName = "Q:\\Downloads\\wps文件转换pdf-new.pdf";
        wordToPdf(fileName,pdfName);
    }

    public static void wordToPdf(String inFilePath, String outFilePath) {
        System.out.println("inFilePath = " + inFilePath);
        System.out.println("outFilePath = " + outFilePath);
        Long start = System.currentTimeMillis();
        //构建ActiveX组件实例
        ActiveXComponent axc = null;
        //初始化Dispatch对象
        Dispatch word = null;
        try {
            //初始化com线程（不初始化会出现异常）
            ComThread.InitSTA();
            /*
                //操作Microsoft Office
                axc = new ActiveXComponent("Word.Application");
            */
            //操作WPS Office
            axc = new ActiveXComponent("KWPS.Application");
            //设置应用操作不开启窗口，在后台静默处理
            axc.setProperty("Visible", new Variant(false));
            //获得该ActiveX控件的控制权
            word = axc.getProperty("Documents").toDispatch();
            //方法参数包装（具体的去看巨硬或者WPS的VBA文档）
            Object[] params1 = {
                    inFilePath,//文档名（可包含路径。我这里是方法直接接收的全路径）
                    new Variant(false),//如果该属性为 True，则当文件不是word格式时，将显示“文件转换”对话框
                    new Variant(true)//如果该属性值为 True，则以只读方式打开文档。该参数不会覆盖保存的文档的只读建议设置。例如，如果文档在只读建议启用的情况下保存，则将 ReadOnly 参数设置为 False 不会导致文件以可读写方式打开
            };
            word = Dispatch.invoke(word, "Open", Dispatch.Method, params1, new int[1]).toDispatch();
            //创建一个文件对象
            File file = new File(outFilePath);
            //检查磁盘中是否已经存在该文件，若存在就删除
            if (file.exists()) {
                boolean delete = file.delete();
            }
            Object[] params2 = {
                    outFilePath,//新的 PDF 或 XPS 文件的路径和文件名
                    new Variant(17)//指定采用 PDF:17 格式或 XPS:18 格式
            };
            Dispatch.invoke(word, "ExportAsFixedFormat", Dispatch.Method, params2, new int[1]);
            Long end = System.currentTimeMillis();
            System.out.println("文档转换成功·用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文档转换失败：" + e.getMessage());
        } finally {
            //关闭Word
            if (word != null) {
                Dispatch.call(word, "Close", false);
            }
            if (axc != null) {
                axc.invoke("Quit", new Variant[]{});
            }
            //关闭com线程（不关闭会损耗系统性能）
            ComThread.Release();
        }
    }
}
