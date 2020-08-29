package com.itheim.service.Impl;

import com.github.pagehelper.PageHelper;
import com.itheim.Product;
import com.itheim.dao.productDao;
import com.itheim.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>productService实现类</p>
 *
 * @author : Dell
 * @date : 2020-08-14 14:16
 **/
@Service
@Transactional
public class productServiceImp  implements productService {
    @Autowired
    private productDao dao;
    @Override
    public List<Product> findAll() throws Exception {

        return dao.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        dao.saveProduct(product);
    }

    /**
     * 分页查询所有
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<Product> findAllByPages(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }
}
