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
        //初始化用户数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001, "赵小凡", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH, userList);
        //初始化图书数据  多态写法
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Java入门", "张三", Constant.TYPE_COMPUTER, "123-1", "机工", Constant.STATUS_STORAGE));
        bookList.add(new Book(1, "Java进阶", "张三", Constant.TYPE_COMPUTER, "123-2", "机工", Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH, bookList);
    }

    /**
     * 初始化数据
     * @param
     */
    public static void initData(String path, List<?> list) {        //泛型
        ObjectOutputStream oos = null;
        //创建相关文件夹
        try {
            File directory = new File(path.split("/")[0]+"/");
            File file = new File(path);
            //判断文件夹是否存在
            if (!directory.exists()) {
                directory.mkdir();
            }
            //判断文件是否存在
            if (!file.exists()) {
                file.createNewFile();
                //利用对象输出流将list数据写出到文件中
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