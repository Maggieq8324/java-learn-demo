package com.coisini.mybatislearn.mapper;

import com.coisini.mybatislearn.model.Product;
import com.coisini.mybatislearn.vo.ProductDetailVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductMapper {

    /**
     * 查询products
     * @return
     */
    List<Product> getProducts();

    /**
     * 查询products
     * 注解编写sql
     * @return
     */
    @Select("select * from product")
    List<Product> getProducts1();

    /**
     * 插入product
     * @param product
     * @return
     */
    long insertProduct(Product product);

    /**
     * 查询明细
     * @param id
     * @return
     */
    ProductDetailVo getDetail(Integer id);

}
