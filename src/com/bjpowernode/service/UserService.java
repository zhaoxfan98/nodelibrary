package com.bjpowernode.service;

import com.bjpowernode.bean.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */
public interface UserService {
    List<User> select();

    void add(User user);

    void update(User user);

    void delete(int id);

    void frozen(int id);

    List<User> selectUserToLend();

    User charge(User user, BigDecimal money);
}
