package com.coisini.springbootlearn.controller.v1;

import com.coisini.springbootlearn.model.Product;
import com.coisini.springbootlearn.core.common.PageCounter;
import com.coisini.springbootlearn.core.common.Paging;
import com.coisini.springbootlearn.repository.ProductRepository;
import com.coisini.springbootlearn.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 商品控制器
 * @author coisini
 * @date Aug 14, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 获取商品分页数据
     * @return
     */
    @GetMapping("/getPageProduct")
    public Paging<Product> getPageSpu(@RequestParam(defaultValue = "0") Integer start,
                                         @RequestParam(defaultValue = "2") Integer count) {
        // 分页参数构造
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);

        // 构造分页查询信息
        Pageable page = PageRequest.of(pageCounter.getPage(), pageCounter.getCount(), Sort.by("id").descending());

        // 查询分页结果
        Page<Product> pageResult = productRepository.findAll(page);

        // 返回分页构造对象
        return new Paging<>(pageResult);
    }

}
