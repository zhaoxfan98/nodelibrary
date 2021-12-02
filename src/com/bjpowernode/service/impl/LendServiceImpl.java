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
     * ��ѯ
     * @param lend
     * @return
     */
    @Override
    public List<Lend> select(Lend lend) {
        return lendDao.select(lend);
    }

    /**
     * ������������
     * @param bookId
     * @param UserId
     */
    @Override
    public void add(int bookId, int UserId) {
        //��ѯͼ������
        Book paramBook = new Book();
        paramBook.setId(bookId);
        List<Book> bookList = bookDao.select(paramBook);

        //��ѯ�û�����
        User paramUser = new User();
        paramUser.setId(UserId);
        List<User> userList = userDao.select(paramUser);

        Lend lend = new Lend();
        //���ɱ��
        lend.setId(UUID.randomUUID().toString());
        //��ͼ�����ֵ
        Book book = bookList.get(0);
        book.setStatus(Constant.STATUS_LEND);
        lend.setBook(book);

        //���û���ֵ
        User user = userList.get(0);
        user.setLend(true);
        lend.setUser(user);

        lend.setStatus(Constant.STATUS_LEND);
        lend.setLendDate(LocalDate.now());
        lend.setReturnDate(LocalDate.now().plusDays(30));

        //�޸�ͼ����û�
        bookDao.update(book);
        userDao.update(user);
        //��ӳ�������
        lendDao.add(lend);
    }

    /**
     * ����
     * @param lend
     * @return
     */
    @Override
    public List<Lend> returnBook(Lend lend) {
        //��ȡ�û������ͼ�����
        Book book = lend.getBook();
        User user = lend.getUser();
        //�޸�״̬
        book.setStatus(Constant.STATUS_STORAGE);
        user.setLend(false);
        userDao.update(user);
        bookDao.update(book);
        //ɾ��lend����
        lendDao.delete(lend.getId());
        //��ѯ ���ظ��º�Ľ��
        return lendDao.select(null);
    }

    /**
     * �޸Ľ�������
     * @param lend
     */
    @Override
    public void update(Lend lend) {
        lendDao.update(lend);
    }
}
