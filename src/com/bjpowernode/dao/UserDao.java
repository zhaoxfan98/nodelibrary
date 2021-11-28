package com.bjpowernode.dao;

import com.bjpowernode.bean.User;

import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */
public interface UserDao {
    List<User> select();

    void add(User user);
}
