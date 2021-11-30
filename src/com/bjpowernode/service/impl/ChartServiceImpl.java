package com.bjpowernode.service.impl;

import com.bjpowernode.dao.ChartDao;
import com.bjpowernode.dao.impl.ChartDaoImpl;
import com.bjpowernode.service.ChartService;

import java.util.Map;

/**
 * Created on 2021/11/30.
 *
 * @author zhaoxfan
 */
public class ChartServiceImpl implements ChartService {
    private ChartDao chartDao = new ChartDaoImpl();

    @Override
    public Map<String, Integer> bookTypeCount() {
        return chartDao.bookTypeCount();
    }
}
