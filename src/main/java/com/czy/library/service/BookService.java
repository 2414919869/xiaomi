package com.czy.library.service;

import com.czy.library.entity.Book;
import com.czy.library.entity.BookExample;
import com.czy.library.mapper.BookMapper;
import com.czy.library.request.BookQueryReq;
import com.czy.library.request.BookSaveReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import util.RedisUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;

    @Resource
    private RedisUtils redisUtils;

    public long count(){
        return bookMapper.countByExample(null);
    }

    public List<Book> selectAll(){
        BookExample bookExample=new BookExample();
        bookExample.setOrderByClause("id asc");
        return bookMapper.selectByExample(bookExample);
    }

    public List<Book> getAll(){
        //从redis中取数据
        String allBooks= redisUtils.get("allBooks");
        //没有则查询并放入缓存
        if (allBooks==null){
            BookExample bookExample=new BookExample();
            bookExample.setOrderByClause("id asc");
            redisUtils.set("allBooks",String.valueOf(bookMapper.selectByExample(bookExample)));
        }
        return bookMapper.selectByExample(null);
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

    public void deleteByRedis(int id){
        // 删除redis缓存数据
        redisUtils.delete("book_" + id);
        // 删除操作
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

    public void updateByPrimaryRedis(BookSaveReq bookSaveReq){
        Book book=new Book();
        BeanUtils.copyProperties(bookSaveReq,book);
        // 删除redis中的缓存
        redisUtils.delete("book_" + book.getId());
        // 修改操作
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

    public void returnBook(BookQueryReq bookQueryReq){
        Book book=new Book();
        BeanUtils.copyProperties(bookQueryReq,book);
        BookExample bookExample=new BookExample();
        bookExample.createCriteria().andBookNameEqualTo(book.getBookName())
                .andAuthorNameEqualTo(book.getAuthorName())
                .andTypeEqualTo(book.getType());
        List<Book> list=bookMapper.selectByExample(bookExample);
        BeanUtils.copyProperties(list.get(0),book);
        int num=book.getNumber();
        book.setNumber(num+1);
        bookMapper.updateByPrimaryKey(book);
    }


}
