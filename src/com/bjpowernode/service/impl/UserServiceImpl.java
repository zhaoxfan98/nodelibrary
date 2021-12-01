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
 * �û�������ص���
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * ��ѯ
     * @return
     */
    @Override
    public List<User> select() {
        //����Dao��д�õķ�������
        return userDao.select();
    }

    /**
     * ���
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * �޸�����
     * @param user
     */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * ɾ������
     * @param id
     */
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    /**
     * �����û�
     * @param id
     */
    @Override
    public void frozen(int id) {
        userDao.frozen(id);
    }

    @Override
    public List<User> selectUserToLend() {
        return userDao.selectUserToLend();
    }
}
