package com.example.file;

public class EdiHttpRequestErrorException extends RuntimeException {
    /**
     * 基异常类
     */
    public EdiHttpRequestErrorException() {
        super("EDI http请求错误异常");
    }

    /**
     * 基异常类
     *
     * @param message 异常信息
     */
    public EdiHttpRequestErrorException(String message) {
        super(message);
    }
}
