package com.czy.library.controller;

import com.czy.library.response.Resp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/resptest")
    public Resp respTest(){
        return Resp.success()
                .data("message","hello")
                .data("time",new Date());
    }
}
