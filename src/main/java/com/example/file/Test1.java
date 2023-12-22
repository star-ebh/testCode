package com.example.file;

import cn.hutool.core.text.StrSplitter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        List<String> split = StrSplitter.split("研发中心/LiDAR群组/车规工程部/标定工艺组/", "/", 0, true, true);
        log.info("结果=>{}", split);
        for (int i = split.size()-1; i >= 0; i--) {
            String string = split.get(i);
            log.info("string=>{}", string);
        }
    }
}
