package com.example.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 功能描述
 *
 * @author Huang Kaihang
 * @version 1.0.0
 * @since 2022-09-26
 */
public class FreeMarkerUtil {
    private FreeMarkerUtil() {
    }

    public static String parseTemplate(String path, String filename, Object data) throws IOException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreeMarkerUtil.class, path);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setWhitespaceStripping(true);
        Template template = configuration.getTemplate(filename);
        // 接收处理后的模版内容
        StringWriter stringWriter = new StringWriter();
        try {
            template.process(data, stringWriter);
            return stringWriter.toString();
        }  catch (freemarker.template.TemplateException e) {
            e.printStackTrace();
        } finally {
            stringWriter.close();
        }
        return "";
    }

    /**
     * Freemarker渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processFreemarker(String template, Object data) {
        if (template == null || data == null)
            return null;
        try {
            Configuration cfg = new Configuration(Configuration.getVersion());
            StringTemplateLoader stl = new StringTemplateLoader();
            stl.putTemplate("邮件", template);
            cfg.setTemplateLoader(stl);
            Template tpl = cfg.getTemplate("邮件");
            StringWriter writer = new StringWriter();
            tpl.process(data, writer);
            return writer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
