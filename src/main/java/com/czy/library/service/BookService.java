package com.czy.library.service;

import com.czy.library.entity.Book;
import com.czy.library.entity.BookExample;
import com.czy.library.mapper.BookMapper;
import com.czy.library.request.BookQueryReq;
import com.czy.library.request.BookSaveReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;

    public long count(){
        return bookMapper.countByExample(null);
    }

    public List<Book> selectAll(){
        BookExample bookExample=new BookExample();
        bookExample.setOrderByClause("id asc");
        return bookMapper.selectByExample(bookExample);
    }

    public Book selectById(int id){
        return bookMapper.selectByPrimaryKey(id);
    }

    public Book selectByName(String book_name){
        BookExample bookExample=new BookExample();
        bookExample.createCriteria().andBookNameEqualTo(book_name);
       List<Book> list=bookMapper.selectByExample(bookExample);
       return list.get(0);
    }

    public void delete(int id){
        bookMapper.deleteByPrimaryKey(id);
    }

    public void save(BookSaveReq bookSaveReq){
        Book book=new Book();
        BeanUtils.copyProperties(bookSaveReq,book);
        bookMapper.insert(book);
    }

    public void updateByPrimaryKey(BookSaveReq bookSaveReq){
        Book book=new Book();
        BeanUtils.copyProperties(bookSaveReq,book);
        bookMapper.updateByPrimaryKey(book);
    }

    public boolean borrowBook(BookQueryReq bookQueryReq){
        Book book=new Book();
        BeanUtils.copyProperties(bookQueryReq,book);
        BookExample bookExample=new BookExample();
        bookExample.createCriteria().andBookNameEqualTo(book.getBookName())
                .andAuthorNameEqualTo(book.getAuthorName())
                .andTypeEqualTo(book.getType());
        List<Book> list=bookMapper.selectByExample(bookExample);
        BeanUtils.copyProperties(list.get(0),book);
        if (book.getNumber()>0){
            int num=book.getNumber();
            book.setNumber(num-1);
            bookMapper.updateByPrimaryKey(book);
            return true;
        } else {
            bookMapper.updateByPrimaryKey(book);
            return false;
        }
    }


}
