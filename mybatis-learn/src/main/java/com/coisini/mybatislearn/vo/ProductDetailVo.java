package com.coisini.mybatislearn.vo;

import com.coisini.mybatislearn.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class ProductDetailVo extends Product {

    private List<String> itemTitleList;

}
