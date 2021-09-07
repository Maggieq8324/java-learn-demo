package com.coisini.springbootlearn.vo;

import com.coisini.springbootlearn.model.Category;
import com.coisini.springbootlearn.model.Coupon;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Coupon - Category Vo
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
@Getter
@Setter
public class CouponCategoryVo extends CouponPureVo {
    private List<CategoryPureVo> categories = new ArrayList<>();

    public CouponCategoryVo(Coupon coupon) {
        super(coupon);
        List<Category> categories = coupon.getCategoryList();
        categories.forEach(category -> {
            CategoryPureVo vo = new CategoryPureVo(category);
            this.categories.add(vo);
        });
    }
}
