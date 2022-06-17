package com.example.controller;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hkh
 */
@Slf4j
@RestController
public class TestController {

    //@Autowired
    //private NettyClientService clientService;

    private final static String KEY = "fKvpK9ubTxAAQFwfSZO1u5XyoKaSGEkj";
    private final static String SECRET = "2qpMxSTtwA1sTnaO";

    String bodyStr ="grant_type=client_credentials&client_id=ojxYrJU7Aec2vVcCKcHZ4hRwA8mJNzAU&client_secret=4BBkxnwgHUnaoIaL";




}
