package com.example.tcp;

import com.example.common.Constant;
import com.google.common.collect.Maps;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hkh
 * @version 1.0.0
 * @ClassName TcpUtil
 * @Description TcpUtil
 * @createTime 2022/6/13 14:15
 */
public class TcpUtil {

    private TcpUtil(){}

    /**
     * 发送TCP请求
     * @see 本方法默认的连接超时和读取超时均为30秒
     * @see 编码与解码请求响应字节时,均采用双方约定的字符集,即本方法的第四个参数reqCharset
     * @param IP         远程主机地址
     * @param port       远程主机端口
     * @param reqData    待发送报文的中文字符串形式
     * @param reqCharset 该方法与远程主机间通信报文的编码字符集(编码为byte[]发送到Server)
     * @return localPort--本地绑定的端口,reqData--请求报文,respData--响应报文,respDataHex--远程主机响应的原始字节的十六进制表示
     */
    public static Map<String, String> sendTcpRequest(String ip, String port, String reqData, String reqCharset){
        Map<String, String> respMap = Maps.newHashMap();
        // 写
        OutputStream out = null;
        // 读
        InputStream in = null;
        // 本地绑定的端口(java socket, client, /127.0.0.1:50804 => /127.0.0.1:9901)
        String localPort = null;
        // 响应报文
        String respData = null;
        // 远程主机响应的原始字节的十六进制表示
        String respDataHex = null;
        // 客户机
        Socket socket = new Socket();
        try {
            socket.setTcpNoDelay(true);
            socket.setReuseAddress(true);
            socket.setSoTimeout(30000);
            socket.setSoLinger(true, 5);
            socket.setSendBufferSize(1024);
            socket.setReceiveBufferSize(1024);
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress(ip, Integer.parseInt(port)), 30000);
            localPort = String.valueOf(socket.getLocalPort());

            // 发送TCP请求
            out = socket.getOutputStream();
            out.write(reqData.getBytes(reqCharset));

            // 接收TCP响应
            in = socket.getInputStream();
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int len = -1;
            while((len=in.read(buffer)) != -1){
                bytesOut.write(buffer, 0, len);
                String temp = bytesOut.toString(reqCharset);
                if (temp.contains(Constant.TCP_BEGIN)||temp.contains(Constant.TCP_ABORT)) {
                    break;
                }
            }

            // 解码TCP响应的完整报文
            respData = bytesOut.toString(reqCharset);
            respDataHex = formatToHexStringWithASCII(bytesOut.toByteArray());
        } catch (Exception e) {
            System.out.println("与[" + ip + ":" + port + "]通信遇到异常,堆栈信息如下");
            e.printStackTrace();
        } finally {
            if (null!=socket && socket.isConnected() && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("关闭客户机Socket时发生异常,堆栈信息如下");
                    e.printStackTrace();
                }
            }
        }
        respMap.put("localPort", localPort);
        respMap.put("reqData", reqData);
        respMap.put("respData", respData);
        respMap.put("respDataHex", respDataHex);
        return respMap;
    }

    /**
     * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
     * @see 该方法会将字节数组中的所有字节均格式化为字符串
     * @see 使用说明详见<code>formatToHexStringWithASCII(byte[], int, int)</code>方法
     */
    private static String formatToHexStringWithASCII(byte[] data){
        return formatToHexStringWithASCII(data, 0, data.length);
    }


    /**
     * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
     * @see 该方法常用于字符串的十六进制打印,打印时左侧为十六进制数值,右侧为对应的字符串原文
     * @see 在构造右侧的字符串原文时,该方法内部使用的是平台的默认字符集,来解码byte[]数组
     * @see 该方法在将字节转为十六进制时,默认使用的是<code>java.util.Locale.getDefault()</code>
     * @see 详见String.format(String, Object...)方法和new String(byte[], int, int)构造方法
     * @param data   十进制的字节数组
     * @param offset 数组下标,标记从数组的第几个字节开始格式化输出
     * @param length 格式长度,其不得大于数组长度,否则抛出java.lang.ArrayIndexOutOfBoundsException
     * @return 格式化后的十六进制字符串
     */
    private static String formatToHexStringWithASCII(byte[] data, int offset, int length){
        int end = offset + length;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append("\r\n------------------------------------------------------------------------");
        boolean chineseCutFlag = false;
        for(int i=offset; i<end; i+=16){
            // X或x表示将结果格式化为十六进制整数
            sb.append(String.format("\r\n%04X: ", i-offset));
            sb2.setLength(0);
            for(int j=i; j<i+16; j++){
                if(j < end){
                    byte b = data[j];
                    //ENG ASCII
                    if(b >= 0){
                        sb.append(String.format("%02X ", b));
                        //不可见字符
                        if(b<32 || b>126){
                            sb2.append(" ");
                        }else{
                            sb2.append((char)b);
                        }
                    }else{
                        //CHA ASCII
                        //汉字前半个字节
                        if(j == i+15){
                            sb.append(String.format("%02X ", data[j]));
                            chineseCutFlag = true;
                            String s = new String(data, j, 2);
                            sb2.append(s);
                            //后半个字节
                        }else if(j == i&&chineseCutFlag){
                            sb.append(String.format("%02X ", data[j]));
                            chineseCutFlag = false;
                            String s = new String(data, j, 1);
                            sb2.append(s);
                        }else{
                            sb.append(String.format("%02X %02X ", data[j], data[j + 1]));
                            String s = new String(data, j, 2);
                            sb2.append(s);
                            j++;
                        }
                    }
                }else{
                    sb.append("   ");
                }
            }
            sb.append("| ");
            sb.append(sb2.toString());
        }
        sb.append("\r\n------------------------------------------------------------------------");
        return sb.toString();
    }

}
