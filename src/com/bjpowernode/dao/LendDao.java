package com.bjpowernode.dao;

import com.bjpowernode.bean.Lend;

import java.util.List;

/**
 * Created on 2021/12/1.
 *
 * @author zhaoxfan
 */
public interface LendDao {
    List<Lend> select(Lend lend);

    void add(Lend lend);
}
