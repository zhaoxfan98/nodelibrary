package com.bjpowernode.service;

import com.bjpowernode.bean.Lend;

import java.util.List;

/**
 * Created on 2021/12/1.
 *
 * @author zhaoxfan
 */
public interface LendService {
    List<Lend> select(Lend lend);
    void add(int bookId, int UserId);
}
