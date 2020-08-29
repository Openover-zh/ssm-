package com.itheim.service;

import com.itheim.Product;

import java.util.List;

public interface productService {
    public List<Product> findAll() throws Exception;

    void saveProduct(Product product);

    List<Product> findAllByPages(int page, int pageSize);
}
