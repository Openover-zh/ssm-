package com.itheim.dao;

import com.itheim.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface travellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{orderId})")
    public List<Traveller> findById(String orderId) throws Exception;

}
