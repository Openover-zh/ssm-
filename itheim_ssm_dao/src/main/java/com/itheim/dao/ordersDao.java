package com.itheim.dao;


import com.itheim.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ordersDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.itheim.dao.productDao.findById"))
    })
    public List<Orders> findAll();


    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "com.itheim.dao.productDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select =
                    "com.itheim.dao.travellerDao.findById")),
            @Result(column = "memberId",property = "member",one = @One(select =
                    "com.itheim.dao.memberDao.findById")),
    }
    )
    public Orders findById(String id) throws Exception;
}
