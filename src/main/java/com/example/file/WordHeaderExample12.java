package com.example.file;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.http.HtmlUtil;
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
        String test ="H12345678";
        System.out.println(isHKCard(test));
        System.out.println(IdcardUtil.isValidCard(test));
        System.out.println(DateUtil.lastMonth().toDateStr());
        System.out.println(DateUtil.format(DateUtil.lastMonth(), "yyyy-MM"));
        System.out.println(DateUtil.format(DateUtil.offsetMonth(DateUtil.parseDate("2023-11-01"), -1), "yyyy-MM"));
        System.out.println(DateUtil.format(DateUtil.offsetMonth(DateUtil.parseDate("2023-11-30"), -1), "yyyy-MM-dd"));
    }


    /**
     * 港澳居民来往内地通行证
     * 规则： H/M + 10位或6位数字
     * 例：H1234567890
     */
    public static Boolean isHKCard(String card) {
        String reg = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$";
        if (card.matches(reg) == false) {
            //港澳居民来往内地通行证号码不合格
            return false;
        } else {
            //校验通过
            return true;
        }
    }

}
