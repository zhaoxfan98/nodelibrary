package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created on 2021/12/1.
 *
 * @author zhaoxfan
 */
public class LendDaoImpl implements LendDao {
    /**
     * ��ѯ
     * @param lend
     * @return
     */
    @Override
    public List<Lend> select(Lend lend) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> lendList = (List<Lend>) ois.readObject();
            if (lend == null || "".equals(lend.getBook().getBookName())) {
                return lendList;
            } else {
                List<Lend> conditionLend = new ArrayList<>();
                //�����ж�
                //�ж�������ѯ����ͬʱ����
                if (!"".equals(lend.getBook().getBookName()) && !"".equals(lend.getBook().getIsbn())) {
                    conditionLend = lendList.stream().filter(l -> l.getBook().getBookName().equals(lend.getBook().getBookName())).collect(Collectors.toList());
                    conditionLend = conditionLend.stream().filter(l -> l.getBook().getIsbn().equals(lend.getBook().getIsbn())).collect(Collectors.toList());
                } else {
                    if (!"".equals(lend.getBook().getBookName())) {
                        //����ͼ������ѯ
                        conditionLend = lendList.stream().filter(l -> l.getBook().getBookName().equals(lend.getBook().getBookName())).collect(Collectors.toList());
                    }
                    if (!"".equals(lend.getBook().getIsbn())) {
                        //����Isbn��ѯ
                        conditionLend = lendList.stream().filter(l -> l.getBook().getIsbn().equals(lend.getBook().getIsbn())).collect(Collectors.toList());
                    }
                    return conditionLend;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    /**
     * ������������
     * @param lend
     */
    @Override
    public void add(Lend lend) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>)ois.readObject();
            list.add(lend);
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
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

    /**
     * ɾ����������
     * @param id
     */
    @Override
    public void delete(String id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>)ois.readObject();
            if (list != null) {
                Lend originLend = list.stream().filter(r -> Objects.equals(id, r.getId())).findFirst().get();
                list.remove(originLend);
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
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

    /**
     * ���������޸�
     * @param lend
     */
    @Override
    public void update(Lend lend) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>)ois.readObject();
            if (list != null) {
                //�Ӽ����в���Ҫ�޸ĵ�����
                Lend originalLend = list.stream().filter(l -> l.getId().equals(lend.getId())).findFirst().get();
                //�޸�
                BeanUtil.populate(originalLend, lend);
                //д�����ļ�
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
