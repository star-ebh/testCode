package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * @author hkh
 */
@Slf4j
@Controller
public class Test2Controller {


    @GetMapping("/test1")
    public String test() throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://sz-dc.test.robosense.cn:9999/dataWarehouse/productionResume/queryBySnAndStation")
                .queryParam("sn", "10#702000123AA￥11#WSB￥12#202305040015￥13#02。01。01￥14#1。0。1￥");
        URI uri = builder.build().encode().toUri();
        //String url = "http://sz-dc.test.robosense.cn:9999/dataWarehouse/productionResume/queryBySnAndStation?sn=" + URLEncoder.encode("10#702000123AA￥11#WSB￥12#202305040015￥13#02。01。01￥14#1。0。1￥", "utf-8");
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        log.info("DcServiceImpl --> queryProductionRecord --> 数据中心返回数据：{}", response);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        return jsonObject.toJSONString();
    }
}
