package com.example.tcp;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author hkh
 * @version 1.0.0
 * @ClassName ClientUtil
 * @Description TODO
 * @createTime 2022/6/9 0:12
 */
@Slf4j
public class ClientUtil {

    public static void initiateConnection(String msg) throws IOException {
        Socket s = null;
        OutputStream oos = null;
        try {
            // 创建一个socket绑定的端口和地址为：9977，本机。
            s = new Socket("127.0.0.1", 8888);
            // 获取到输出流
            oos = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oos));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = br.readLine()) != null) {
                log.info("{}", line);
                bw.write(line.toCharArray());
                //将内容写到控制台
                bw.flush();
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (s != null) {
                s.close();
            }

        }
    }


    /**
     * Socket客户端
     */
    public static void test() {
        String requestMsg = "client xml or json 数据";
        String address = "127.0.0.1";
        int port = 8888;
        send(address, port, requestMsg);

    }

    /**
     * Socket 客户端请求
     *
     * @param address    ip地址
     * @param port       端口
     * @param requestMsg 请求内容
     */
    public static void send(String address, int port, String requestMsg) {

        try {
            //创建Socket对象
            Socket socket = new Socket(address, port);
            /**
             * 根据输入输出流和服务端连接
             * 1）获取一个输出流，向服务端发送信息
             * 2）将输出流包装成打印流
             * 3）关闭输出流
             */
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(requestMsg);
            printWriter.flush();
            socket.shutdownOutput();
            //获取一个输入流，接收服务端的信息
            InputStream inputStream = socket.getInputStream();
            //包装成字符流，提高效率
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //缓冲区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer sb = new StringBuffer();
            //临时变量
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp).append("\n");
            }
            System.out.println("客户端接收服务端发送信息：" + sb.toString());

            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
