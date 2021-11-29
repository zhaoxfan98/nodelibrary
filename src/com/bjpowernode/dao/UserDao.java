package com.bjpowernode.dao;

import com.bjpowernode.bean.User;

import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */
public interface UserDao {
    //查询
    List<User> select();
    //增加
    void add(User user);
    //修改
    void update(User user);
    //删除
    void delete(int id);
    //冻结
    void frozen(int id);

}
