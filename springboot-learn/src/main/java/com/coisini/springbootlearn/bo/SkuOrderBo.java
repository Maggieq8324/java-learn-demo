package com.coisini.springbootlearn.bo;

import com.coisini.springbootlearn.dto.SkuInfoDTO;
import com.coisini.springbootlearn.model.Sku;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * @Description Sku Order Business Object
 * @author coisini
 * @date Aug 22, 2021
 * @Version 1.0
 */
@Getter
@Setter
public class SkuOrderBo {

    private BigDecimal actualPrice;
    private Integer count;
    private Long categoryId;

    public SkuOrderBo(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.actualPrice = sku.getActualPrice();
        this.count = skuInfoDTO.getCount();
        this.categoryId = sku.getCategoryId();
    }

    /**
     * 获取总价 = 真实价格 * 数量
     * @return
     */
    public BigDecimal getTotalPrice() {
        return actualPrice.multiply(new BigDecimal(this.count));
    }


}
