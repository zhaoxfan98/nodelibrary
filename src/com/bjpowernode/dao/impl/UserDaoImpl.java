package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */

/**
 * 用户Dao层
 */
public class UserDaoImpl implements UserDao {

    /**
     * 从硬盘文件中读取数据
     * @return
     */
    @Override
    public List<User> select() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH))) {
            List<User> list = (List<User>)ois.readObject();
            return  list;
        }catch (Exception e) {
            e.printStackTrace();
        }
        //出现异常，返回List对象
        return new ArrayList<>();
    }

    /**
     * 添加操作
     * @param user
     */
    @Override
    public void add(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //读取文件中的List对象
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            if (list != null) {
                //生成用户的编号
                //获取list中最后的User对象
                User lastUser = list.get(list.size() - 1);
                //生成用户的编号
                user.setId(lastUser.getId()+1);
                //将User对象放入到list中，然后将list写出到文件中
                list.add(user);
            } else {
                list = new ArrayList<>();
                user.setId(1001);
                list.add(user);
            }
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
