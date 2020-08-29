package com.itheim.service;

import com.itheim.Orders;



import java.util.List;

public interface ordersService {
    public List<Orders> findAll();

    public List<Orders> findAllByPages(int page,int pageSize);

    public Orders findById(String id) throws Exception;
}
