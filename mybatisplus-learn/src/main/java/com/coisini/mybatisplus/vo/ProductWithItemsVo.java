package com.coisini.mybatisplus.vo;

import com.coisini.mybatisplus.model.Product;
import com.coisini.mybatisplus.model.ProductItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductWithItemsVo {

    private Integer id;

    private String title;

    List<ProductItem> items;

    /**
     * 构造ProductWithItemsVo对象
     * @param product
     * @param items
     */
    public ProductWithItemsVo(Product product, List<ProductItem> items) {
        BeanUtils.copyProperties(product, this);
        this.setItems(items);
    }
}
