package com.czy.library.controller;

import com.czy.library.response.Resp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${test.nacos}")
    private String testNacos;

    @GetMapping("/test1")
    public String test(){
        return "success";
    }

    @GetMapping("/resptest")
    public Resp respTest(){
        return Resp.success()
                .data("message","hello")
                .data("time",new Date());
    }

    @GetMapping("/hello")
    public Resp nacosTest(){
        return Resp.success()
                .data("message",String.format("Hello %s!",testNacos))
                .data("time",new Date());
    }
}
