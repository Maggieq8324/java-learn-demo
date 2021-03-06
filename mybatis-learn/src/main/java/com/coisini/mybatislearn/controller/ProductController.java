package com.coisini.mybatislearn.controller;

import com.coisini.mybatislearn.model.Product;
import com.coisini.mybatislearn.service.ProductService;
import com.coisini.mybatislearn.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 查询明细
     * 多表查询
     * @param id
     * @return
     */
    @GetMapping("/{id}/detail")
    public ProductDetailVo getDetail(@PathVariable(value = "id") Integer id) {
        return productService.getDetail(id);
    }

}
