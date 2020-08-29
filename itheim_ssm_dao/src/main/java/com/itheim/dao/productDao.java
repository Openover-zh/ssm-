package com.itheim.dao;

import com.itheim.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>product持久层接口</p>
 *
 * @author : Dell
 * @date : 2020-08-14 14:07
 **/

public interface productDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll();
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);


    @Select("select * from product where id=#{productId}")
    public Product findById(String productId) throws Exception;
}
