package com.czy.library.controller;


import com.czy.library.response.Resp;
import com.czy.library.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/count")
    public Resp count(){
        return Resp.success()
                .data("message",bookService.count())
                .data("time",new Date());
    }
}
