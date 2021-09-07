package com.coisini.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coisini.mybatisplus.mapper.ProductItemMapper;
import com.coisini.mybatisplus.mapper.ProductMapper;
import com.coisini.mybatisplus.model.Product;
import com.coisini.mybatisplus.model.ProductItem;
import com.coisini.mybatisplus.service.ProductService;
import com.coisini.mybatisplus.vo.PageResponseVo;
import com.coisini.mybatisplus.vo.ProductWithItemsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductItemMapper productItemMapper;

    /**
     * 查询products
     * @return
     */
    @Override
    public List<Product> getProducts() {
        return productMapper.selectList(null);
    }

    /**
     * 获取分页数据
     * @param page
     * @param count
     * @return
     */
    @Override
    public PageResponseVo<Product> getProductPage(Integer page, Integer count) {
        Page<Product> pager = new Page<>(page, count);

        /**
         * 与 IPage<Product> paging = productMapper.selectPage(pager, null)相同
         */
        IPage<Product> paging = this.getBaseMapper().selectPage(pager, null);
        return new PageResponseVo(paging.getTotal(), paging.getRecords(), paging.getCurrent(), paging.getSize());
    }

    @Override
    public ProductWithItemsVo getWithItems(Integer id) {
        Product product = this.getById(id);
        if (Objects.isNull(product)) {
            System.out.println("未查询到product");
            return null;
        }

        /**
         * QueryWrapper的使用
         * wrapper.eq("banner_id", id)
         * banner_id 数据库字段
         * id 判断相等的值
         */
//        QueryWrapper<ProductItem> wrapper = new QueryWrapper<>();
//        wrapper.eq("product_id", id);
//        List<ProductItem> productItems = productItemMapper.selectList(wrapper);

        /**
         * QueryWrapper lambda的使用
         */
//        QueryWrapper<ProductItem> wrapper = new QueryWrapper<>();
//        /**
//         * lambda方法引用
//         */
//        wrapper.lambda().eq(ProductItem::getProductId, id);
//        List<ProductItem> productItems = productItemMapper.selectList(wrapper);


        /**
         * LambdaQueryWrapper的使用
         */
//        LambdaQueryWrapper<ProductItem> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ProductItem::getProductId, id);
//        List<ProductItem> productItems = productItemMapper.selectList(wrapper);

        /**
         * 链式调用
         */
        List<ProductItem> productItems =
                new LambdaQueryChainWrapper<>(productItemMapper)
                        .eq(ProductItem::getProductId, id)
                        .list();

        return new ProductWithItemsVo(product, productItems);
    }

}
