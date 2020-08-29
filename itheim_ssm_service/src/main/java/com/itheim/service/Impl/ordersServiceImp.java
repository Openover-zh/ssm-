package com.itheim.service.Impl;

import com.github.pagehelper.PageHelper;
import com.itheim.Orders;
import com.itheim.dao.ordersDao;
import com.itheim.service.ordersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>orderService实现类</p>
 *
 * @author : Dell
 * @date : 2020-08-20 17:43
 **/
@Service
@Transactional
public class ordersServiceImp  implements ordersService {
    @Autowired
    private ordersDao dao;
    @Override
    public List<Orders> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Orders> findAllByPages(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }
    @Override
    public Orders findById(String id) throws Exception {
        return dao.findById(id);
    }


}
