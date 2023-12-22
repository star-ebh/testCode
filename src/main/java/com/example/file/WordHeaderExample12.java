package com.example.file;

import cn.hutool.core.io.IoUtil;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;

import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordHeaderExample12 {
    public static void main(String[] args) {
        String html = "<html><head><title>Example</title></head><body><h1>Hello, World!</h1><p>This is an example.</p><img src=\"image.jpg\"><table><tr><td>Cell 1</td><td>Cell 2</td></tr></table></body></html>";

        boolean hasVisibleContent = hasVisibleContent(html);
        System.out.println("Has visible content: " + hasVisibleContent);
    }

    public static boolean hasVisibleContent(String html) {
        // 定义匹配HTML标签的正则表达式
        String tagRegex = "<[^>]+>";
        // 定义匹配HTML文字、图片、表格的正则表达式
        String contentRegex = "<[^>]*>(.|[\n\r])*<[^>]*>";

        // 移除所有的HTML标签
        String plainContent = html.replaceAll(tagRegex, "");

        // 利用正则表达式匹配文字、图片、表格内容
        Pattern contentPattern = Pattern.compile(contentRegex);
        Matcher contentMatcher = contentPattern.matcher(plainContent);

        // 查找匹配的内容
        while (contentMatcher.find()) {
            String content = contentMatcher.group();

            // 判断内容是否为空
            if (!content.trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }

}
