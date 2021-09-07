package com.coisini.mybatislearn.controller;

import com.coisini.mybatislearn.model.Product;
import com.coisini.mybatislearn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询Products
     * @return
     */
    @GetMapping("/select")
    public List<Product> selectProducts() {
        return productService.getProducts();
    }

    /**
     * 查询Products
     * @return
     */
    @GetMapping("/select1")
    public List<Product> getProducts1() {
        return productService.getProducts1();
    }

    /**
     * 插入product
     * @return
     */
    @GetMapping("/insert")
    public long insertProduct() {
        return productService.insertProduct();
    }

}
