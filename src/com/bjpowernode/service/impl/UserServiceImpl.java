package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.UserService;

import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */

/**
 * 用户服务相关的类
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 查询
     * @return
     */
    @Override
    public List<User> select() {
        //调用Dao层写好的方法即可
        return userDao.select();
    }

    /**
     * 添加
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }
}
