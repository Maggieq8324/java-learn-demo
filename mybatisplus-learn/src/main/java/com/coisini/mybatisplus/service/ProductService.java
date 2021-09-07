package com.coisini.mybatisplus.service;

import com.coisini.mybatisplus.model.Product;
import com.coisini.mybatisplus.vo.PageResponseVo;
import com.coisini.mybatisplus.vo.ProductWithItemsVo;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    PageResponseVo<Product> getProductPage(Integer page, Integer count);

    ProductWithItemsVo getWithItems(Integer id);
}
