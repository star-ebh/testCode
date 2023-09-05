package com.example.word;

import com.deepoove.poi.XWPFTemplate;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TemplateWordToPdf {
    public static void main(String[] args) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "郭德纲");
        XWPFTemplate template = XWPFTemplate.compile("E:\\录用模板.docx").render(data);
        template.writeToFile("E:\\out_example_okr.docx");
    }
}
