package com.coisini.mybatisplus.controller;

import com.coisini.mybatisplus.vo.PageResponseVo;
import com.coisini.mybatisplus.model.Product;
import com.coisini.mybatisplus.service.ProductService;
import com.coisini.mybatisplus.vo.ProductWithItemsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductWithItemsVo getWithItems(@PathVariable Integer id) {
        return productService.getWithItems(id);
    }

    /**
     * 查询Products
     * @return
     */
    @GetMapping("/select")
    public List<Product> selectProducts() {
        return productService.getProducts();
    }

    /**
     * 查询Product分页
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/page")
    public PageResponseVo<Product> selectProductPage(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer count) {
        return productService.getProductPage(page, count);
    }
}
