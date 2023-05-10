package com.example.thread;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController(value = "/requestContextHolder")
public class RequestContextHolderTest {


    @GetMapping(value = "/test")
    public void testAdd() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 获取请求头test的值
        String test = Convert.toStr(requestAttributes.getAttribute("test", RequestAttributes.SCOPE_REQUEST));
        Console.log("test============>{}", test);
        RequestContextHolder.setRequestAttributes(requestAttributes, true);

    }
}
