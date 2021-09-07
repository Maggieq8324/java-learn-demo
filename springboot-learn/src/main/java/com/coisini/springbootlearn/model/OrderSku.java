package com.coisini.springbootlearn.model;

import com.coisini.springbootlearn.dto.SkuInfoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description Order Sku 模型
 * @author coisini
 * @date Aug 22, 2021
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderSku {

    private Long id;
    private Long spuId;
    /**
     * 最终价格 singlePrice * count
     */
    private BigDecimal finalPrice;
    /**
     * Sku 单价
     */
    private BigDecimal singlePrice;
    /**
     * Sku 规格
     */
    private List<String> specValues;
    /**
     * Sku 数量
     */
    private Integer count;
    private String img;
    private String title;

    public OrderSku(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.id = sku.getId();
        this.spuId = sku.getSpuId();
        this.singlePrice = sku.getActualPrice();
        this.finalPrice = sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
        this.count = skuInfoDTO.getCount();
        this.img = sku.getImg();
        this.title = sku.getTitle();
        this.specValues = sku.getSpecValueList();
    }
}

