package com.example.code;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jodd.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Test {
    public static void main(String[] args) throws IOException {
//        String orgName = "上海路泊盛世信息科技有限公司";
//        String buyName = "上海路泊盛世科技信息有限公司";
//        boolean isCompanyError = !StringUtils.trim(orgName).equals(StringUtils.trim(buyName))
//                && !StringUtils.trim(orgName).equals("上海速腾聚创科技有限公司");
//        System.out.println(isCompanyError);
//        getRemoteFile();
        AES aes = SecureUtil.aes();
        System.out.println(aes.encryptHex("250233241045624421986568281030997902990"));
    }



    public static void getRemoteFile() throws IOException {
        JSONArray array = new JSONArray();

        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", "aaron.huang", "H.202304");
//            String remoteUrl = "smb://10.10.0.51";
        String remoteUrl = "smb://10.10.0.51/";
//        String remoteUrl = "smb://192.168.1.10/it_datas/开发组文件/测试文件夹/";
        SmbFile remoteFile = new SmbFile(remoteUrl, auth);
        remoteFile.connect();//尝试连接
        getAllDir(array, remoteFile);
        System.out.println(array.toJSONString());
//        if (remoteFile.exists()) {
//            // 获取共享文件夹中文件列表
//            SmbFile[] smbFiles = remoteFile.listFiles();
//            for (SmbFile smbFile : smbFiles) {
//                if (smbFile.isDirectory()) {
//                    JSONObject item = new JSONObject();
//                    String parent = smbFile.getParent();
//                    System.out.println("parent==>" + parent);
//                    item.put("parent", parent);
//
//
//                    String path = smbFile.getPath();
//                    System.out.println("path==>" + path);
//                    item.put("path", path);
//
//                    String uncPath = smbFile.getUncPath();
//                    System.out.println("uncPath==>" + uncPath);
//                    item.put("uncPath", uncPath);
//
//                    String name = smbFile.getName();
//                    item.put("name", name);
//                    System.out.println("名称==>" + name);
//
//                    System.out.println("isDirectory=》" + smbFile.isDirectory());
//                    array.add(item);
//                }
//            }
//        }
    }

    private static void getAllDir(JSONArray array, SmbFile remoteFile) throws SmbException {
        if (Objects.isNull(remoteFile) || !remoteFile.exists()) {
            return;
        }
        SmbFile[] smbFiles = remoteFile.listFiles();
        for (SmbFile itemFile : smbFiles) {
//            if (itemFile.isDirectory()) {
            JSONObject itemJson = new JSONObject();
            String parent = itemFile.getParent();
            System.out.println("parent==>" + parent);
            itemJson.put("parent", parent);

            String path = itemFile.getPath();
            System.out.println("path==>" + path);
            itemJson.put("path", path);

            String uncPath = itemFile.getUncPath();
            System.out.println("uncPath==>" + uncPath);
            itemJson.put("uncPath", uncPath);

            String name = itemFile.getName();
            itemJson.put("name", name);
            itemJson.put("layer", "1");
            System.out.println("名称==>" + name);

            System.out.println("isDirectory=》" + itemFile.isDirectory());
            array.add(itemJson);
            getAllDir(array, itemFile);
//            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        String url = "https://moka-co-oss.mokahr.com/robosense/616e46d6-fd44-4844-8015-47589186048f.pdf?OSSAccessKeyId=LTAI5tBpSc3ffR7XK27tJwPq&Expires=1714548225&Signature=Az8X%2FU8ReIKVXx3ZgKCy4NgARas%3D&response-content-disposition=attachment%3B%20filename%3D%22%25E7%258E%258B%25E5%25B8%25AE%25E9%259B%2584%25E7%259A%2584%25E7%25AE%2580%25E5%258E%2586.pdf%22%3B%20filename*%3Dutf-8''%25E7%258E%258B%25E5%25B8%25AE%25E9%259B%2584%25E7%259A%2584%25E7%25AE%2580%25E5%258E%2586.pdf";
//        InputStream inputStream = HttpRequest.get(url).setFollowRedirects(true).execute().bodyStream();
//        byte[] bytes = IoUtil.readBytes(inputStream);
//        System.out.println(bytes.length);
//        InputStream inputStream2 = HttpClientUtil.doGetFileInputStream(url);
//        byte[] bytes2 = IoUtil.readBytes(inputStream2);
//        System.out.println(bytes2.length);
//    }
//public static void main(String[] args) {
//    System.out.println(new BigDecimal(-1).compareTo(BigDecimal.ZERO));
//}
}
