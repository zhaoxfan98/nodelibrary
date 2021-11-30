package com.bjpowernode.util;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/11/28.
 *
 * @author zhaoxfan
 */
public class InitDataUtil {
    public static void main(String[] args) {
        //��ʼ���û�����
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001, "��С��", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH, userList);
        //��ʼ��ͼ������  ��̬д��
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Java����", "����", Constant.TYPE_COMPUTER, "123-1", "����", Constant.STATUS_STORAGE));
        bookList.add(new Book(1, "Java����", "����", Constant.TYPE_COMPUTER, "123-2", "����", Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH, bookList);
    }

    /**
     * ��ʼ������
     * @param
     */
    public static void initData(String path, List<?> list) {        //����
        ObjectOutputStream oos = null;
        //��������ļ���
        try {
            File directory = new File(path.split("/")[0]+"/");
            File file = new File(path);
            //�ж��ļ����Ƿ����
            if (!directory.exists()) {
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if (!file.exists()) {
                file.createNewFile();
                //���ö����������list����д�����ļ���
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}