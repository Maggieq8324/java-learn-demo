/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-05-14 05:08
 */
package com.coisini.mybatislearn.service;

import com.coisini.mybatislearn.mapper.ProductMapper;
import com.coisini.mybatislearn.model.Product;
import com.coisini.mybatislearn.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询products
     * @return
     */
    public List<Product> getProducts() {
        return productMapper.getProducts();
    }

    /**
     * 查询products
     * @return
     */
    public List<Product> getProducts1() {
        return productMapper.getProducts1();
    }

    /**
     * 插入product
     * @return
     */
    public long insertProduct() {
        Product product = new Product();
        product.setTitle("NewProduct");

        productMapper.insertProduct(product);
        return product.getId();
    }

    public ProductDetailVo getDetail(Integer id) {
        return productMapper.getDetail(id);
    }
}
