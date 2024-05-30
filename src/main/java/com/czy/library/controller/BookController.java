package com.czy.library.controller;


import com.czy.library.request.BookQueryReq;
import com.czy.library.request.BookSaveReq;
import com.czy.library.response.Resp;
import com.czy.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/count")
    public Resp count(){
        return Resp.success()
                .data("result",bookService.count())
                .data("time",new Date());
    }

    @GetMapping("/all")
    public Resp queryAll(){
        return Resp.success()
                .data("result",bookService.selectAll())
                .data("time",new Date());
    }

    @GetMapping("/{id}")
    public Resp selectById(@PathVariable int id){
        return Resp.success()
                .data("result",bookService.selectById(id))
                .data("time",new Date());
    }

    @GetMapping("/select/{bookName}")
    public Resp selectByName(@PathVariable String bookName){
        return Resp.success()
                .data("result",bookService.selectByName(bookName))
                .data("time",new Date());
    }

    @DeleteMapping("/del/{id}")
    public Resp deleteById(@PathVariable int id){
        bookService.delete(id);
        return Resp.success()
                .data("time",new Date());
    }

    @PostMapping("/save")
    public Resp save(@Valid @RequestBody BookSaveReq req){
        bookService.save(req);
        return Resp.success()
                .data("time",new Date());
    }

    @PostMapping("/update")
    public Resp update(@Valid @RequestBody BookSaveReq req){
        bookService.updateByPrimaryKey(req);
        return Resp.success()
                .data("time",new Date());
    }

    @PostMapping("/borrow")
    public Resp borrow(@Valid @RequestBody BookQueryReq req){
        if (bookService.borrowBook(req)){
            return Resp.success()
                    .data("time",new Date());
        } else {
            return Resp.error()
                    .data("time",new Date());
        }
    }

    @PostMapping("/return")
    public Resp returnBook(@Valid @RequestBody BookQueryReq req){
        bookService.returnBook(req);
        return Resp.success()
                .data("time",new Date());
    }

}
