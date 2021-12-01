package com.bjpowernode.dao;

import com.bjpowernode.bean.User;

import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */
public interface UserDao {
    //��ѯ
    List<User> select();
    //��ѯ����
    List<User> select(User user);

    //����
    void add(User user);
    //�޸�
    void update(User user);
    //ɾ��
    void delete(int id);
    //����
    void frozen(int id);
    //��ѯ���Խ�����û�
    List<User> selectUserToLend();
}
