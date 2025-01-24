//package com.example.common.utils;
//
//import android.content.res.AXmlResourceParser;
//import android.util.TypedValue;
//import cn.hutool.core.util.StrUtil;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.multipart.MultipartFile;
//import org.xmlpull.v1.XmlPullParser;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
///**
// * @author hkh
// * @version 1.0.0
// * @Description apk信息工具类
// * @createTime 2022年03月03日 15:23:00
// */
//@Slf4j
//public class ApkUtils {
//
//    private static final float[] RADIX_MULTS = {0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F};
//    private static final String[] DIMENSION_UNITS = {"px", "dip", "sp", "pt", "in", "mm", "", ""};
//    private static final String[] FRACTION_UNITS = {"%", "%p", "", "", "", "", "", ""};
//    private static final String ANDROID_MANIFEST_FILE_NAME = "androidmanifest.xml";
//    private static final String CONFIG_PACKAGE_NAME = "package";
//    private static final String CONFIG_VERSION_CODE_NAME = "versionCode";
//    private static final String CONFIG_VERSION_NAME = "versionName";
//    private static final int NUMBER = 24;
//
//    @Data
//    @NoArgsConstructor
//    @Accessors(chain = true)
//    public static class ApkInfo {
//        /**
//         * 包名
//         */
//        private String packageName;
//        /**
//         * 版本编码
//         */
//        private String versionCode;
//
//        /**
//         * 版本名
//         */
//        private String versionName;
//    }
//
//    public static ApkInfo getApkFileInfo(MultipartFile apkFile) throws Exception {
//        if (apkFile == null) {
//            throw new RuntimeException("上传的文件为空,请重新上传！");
//        }
//        // 获取包信息apk信息的返回结果
//        String[] apkResult = new String[3];
//        ZipInputStream zs = new ZipInputStream(apkFile.getInputStream());
//        BufferedInputStream bs = new BufferedInputStream(zs);
//        ZipEntry zipEntry;
//        while ((zipEntry = zs.getNextEntry()) != null) {
//            if (zipEntry.isDirectory()) {
//                continue;
//            }
//            byte[] bytes;
//            if (ANDROID_MANIFEST_FILE_NAME.equals(zipEntry.getName().toLowerCase())) {
//                bytes = new byte[(int) zipEntry.getSize()];
//                bs.read(bytes, 0, (int) zipEntry.getSize());
//                InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//                apkResult = getApkInfo(byteArrayInputStream);
//            }
//        }
//        bs.close();
//        zs.close();
//        if (StrUtil.isBlank(apkResult[0])) {
//            throw new RuntimeException("未获取包名,请检测包");
//        }
//        return new ApkInfo()
//                .setPackageName(apkResult[0])
//                .setVersionCode(apkResult[1])
//                .setVersionName(apkResult[2]);
//    }
//
//    private static String[] getApkInfo(InputStream fileInputStream) throws Exception {
//
//        final String[] apkResult = new String[3];
//
//        AXmlResourceParser parser = new AXmlResourceParser();
//        parser.open(fileInputStream);
//        while (true) {
//            int type = parser.next();
//            if (type == XmlPullParser.END_DOCUMENT) {
//                break;
//            }
//            if (type == XmlPullParser.START_TAG) {
//                for (int i = 0; i != parser.getAttributeCount(); ++i) {
//                    if (CONFIG_PACKAGE_NAME.equals(parser.getAttributeName(i))) {
//                        apkResult[0] = getAttributeValue(parser, i);
//                    } else if (CONFIG_VERSION_CODE_NAME.equals(parser.getAttributeName(i))) {
//                        apkResult[1] = getAttributeValue(parser, i);
//                    } else if (CONFIG_VERSION_NAME.equals(parser.getAttributeName(i))) {
//                        apkResult[2] = getAttributeValue(parser, i);
//                    }
//                }
//            }
//        }
//        return apkResult;
//    }
//
//    private static String getAttributeValue(AXmlResourceParser parser, int index) {
//        int type = parser.getAttributeValueType(index);
//        int data = parser.getAttributeValueData(index);
//        if (type == TypedValue.TYPE_STRING) {
//            return parser.getAttributeValue(index);
//        }
//        if (type == TypedValue.TYPE_ATTRIBUTE) {
//            return String.format("?%s%08X", getPackage(data), data);
//        }
//        if (type == TypedValue.TYPE_REFERENCE) {
//            return String.format("@%s%08X", getPackage(data), data);
//        }
//        if (type == TypedValue.TYPE_FLOAT) {
//            return String.valueOf(Float.intBitsToFloat(data));
//        }
//        if (type == TypedValue.TYPE_INT_HEX) {
//            return String.format("0x%08X", data);
//        }
//        if (type == TypedValue.TYPE_INT_BOOLEAN) {
//            return data != 0 ? "true" : "false";
//        }
//        if (type == TypedValue.TYPE_DIMENSION) {
//            return complexToFloat(data) + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
//        }
//        if (type == TypedValue.TYPE_FRACTION) {
//            return complexToFloat(data) + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
//        }
//        if (type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT) {
//            return String.format("#%08X", data);
//        }
//        if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
//            return String.valueOf(data);
//        }
//        return String.format("<0x%X, type 0x%02X>", data, type);
//    }
//
//    /**
//     * 获取包名
//     *
//     * @param id
//     * @return
//     */
//    private static String getPackage(int id) {
//        if (id >>> NUMBER == 1) {
//            return "android:";
//        }
//        return "";
//    }
//
//    private static float complexToFloat(int complex) {
//        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
//    }
//}
