package com.example.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * @author hkh
 * @version 1.0.0
 * @Description 原生tcp客户端
 * @createTime 2022年03月14日 17:43:00
 */
@Slf4j
public class PrimitiveTcpClient implements Runnable {

    @Override
    public void run() {
        Socket s = null;
        try {
            // 创建一个socket绑定的端口和地址为：9977，本机。
            s= new Socket("10.10.0.81", 7013);
            // 获取到输出流
            OutputStream oos = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oos));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            //获取一个输入流，接收服务端的信息
            InputStream inputStream = s.getInputStream();
            //包装成字符流，提高效率
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //缓冲区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = br.readLine()) != null) {
                log.info("{}",bufferedReader.readLine());
                bw.write(line.toCharArray());
                bw.flush();//将内容写到控制台
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}