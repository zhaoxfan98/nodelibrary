package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.BookDaoImpl;
import com.bjpowernode.dao.impl.LendDaoImpl;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.LendService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2021/12/1.
 *
 * @author zhaoxfan
 */
public class LendServiceImpl implements LendService {
    private LendDao lendDao = new LendDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    /**
     * 查询
     * @param lend
     * @return
     */
    @Override
    public List<Lend> select(Lend lend) {
        return lendDao.select(lend);
    }

    /**
     * 新增借阅数据
     * @param bookId
     * @param UserId
     */
    @Override
    public void add(int bookId, int UserId) {
        //查询图书数据
        Book paramBook = new Book();
        paramBook.setId(bookId);
        List<Book> bookList = bookDao.select(paramBook);

        //查询用户数据
        User paramUser = new User();
        paramUser.setId(UserId);
        List<User> userList = userDao.select(paramUser);

        Lend lend = new Lend();
        //生成编号
        lend.setId(UUID.randomUUID().toString());
        //给图书对象赋值
        Book book = bookList.get(0);
        book.setStatus(Constant.STATUS_LEND);
        lend.setBook(book);

        //给用户赋值
        User user = userList.get(0);
        user.setLend(true);
        lend.setUser(user);

        lend.setStatus(Constant.STATUS_LEND);
        lend.setLendDate(LocalDate.now());
        lend.setReturnDate(LocalDate.now().plusDays(30));

        //修改图书和用户
        bookDao.update(book);
        userDao.update(user);
        //添加出借数据
        lendDao.add(lend);
    }

    /**
     * 还书
     * @param lend
     * @return
     */
    @Override
    public List<Lend> returnBook(Lend lend) {
        //获取用户对象和图书对象
        Book book = lend.getBook();
        User user = lend.getUser();
        //修改状态
        book.setStatus(Constant.STATUS_STORAGE);
        user.setLend(false);
        userDao.update(user);
        bookDao.update(book);
        //删除lend数据
        lendDao.delete(lend.getId());
        //查询 返回更新后的结果
        return lendDao.select(null);
    }

    /**
     * 修改借阅数据
     * @param lend
     */
    @Override
    public void update(Lend lend) {
        lendDao.update(lend);
    }
}
