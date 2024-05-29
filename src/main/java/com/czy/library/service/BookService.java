package com.czy.library.service;

import com.czy.library.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;



    public int count(){
        return bookMapper.count();
    }
}
