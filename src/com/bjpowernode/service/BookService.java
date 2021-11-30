package com.bjpowernode.service;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * Created on 2021/11/30.
 *
 * @author zhaoxfan
 */
public interface BookService {
    List<Book> select(Book book);
    void add(Book book);
    void delete(int id);
    void update(Book book);
}
