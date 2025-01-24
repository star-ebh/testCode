package com.example.qrcode;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class qrcode {
    public static void main(String[] args) {
        QrConfig config = new QrConfig(300, 300);
// 高纠错级别
//        config.setErrorCorrection(ErrorCorrectionLevel.L);
        QrCodeUtil.generate("https://bpm-dev.robosense.cn/portal/r/w?sid=ck&cmd=CLIENT_DW_FORM_OPENPAGE&bindid=484cb0f5-2cc6-475f-a1ff-9638a83eaff6#/", config,
                FileUtil.file("E:\\hkh\\project\\bpm-client-script\\资产\\test-qrCode111.jpg"));
    }
}
