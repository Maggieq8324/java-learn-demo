package com.coisini.springbootlearn.controller.v1;

import com.coisini.springbootlearn.core.annotation.ScopeLevel;
import com.coisini.springbootlearn.core.common.LocalUser;
import com.coisini.springbootlearn.core.common.UnifyMessage;
import com.coisini.springbootlearn.core.enumeration.CouponStatus;
import com.coisini.springbootlearn.exception.http.ParameterException;
import com.coisini.springbootlearn.model.Coupon;
import com.coisini.springbootlearn.model.User;
import com.coisini.springbootlearn.service.CouponService;
import com.coisini.springbootlearn.vo.CouponCategoryVo;
import com.coisini.springbootlearn.vo.CouponPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 优惠劵控制器
 * @author coisini
 * @date Aug 20, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 根据二级分类ID查询优惠劵
     * @param cid 二级分类ID
     * @return
     */
    @GetMapping("/by/category/{cid}")
    public List<CouponPureVo> getCouponListByCategory(@PathVariable Long cid) {
        List<Coupon> coupons = couponService.getByCategory(cid);
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        List<CouponPureVo> vos = CouponPureVo.getList(coupons);
        return vos;
    }

    /**
     * 查询全场劵
     * @param
     * @return
     */
    @GetMapping("/whole_store")
    public List<CouponPureVo> getWholeStoreCouponList() {
        List<Coupon> coupons = couponService.getWholeStoreCoupons();
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return CouponPureVo.getList(coupons);
    }

    /**
     * 优惠劵领取
     * @param id
     */
    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public void collectCoupon(@PathVariable Long id) {
        Long uid = LocalUser.getUser().getId();
        couponService.collectOneCoupon(uid, id);
        UnifyMessage.createSuccess(0);
    }

    /**
     * 我的优惠劵
     * @param status
     * @return
     */
    @ScopeLevel
    @GetMapping("/myself/by/status/{status}")
    public List<CouponPureVo> getMyCouponByStatus(@PathVariable Integer status) {
        Long uid = LocalUser.getUser().getId();
        List<Coupon> couponList;

        switch (CouponStatus.toType(status)) {
            case AVAILABLE:
                couponList = couponService.getMyAvailableCoupons(uid);
                break;
            case USED:
                couponList = couponService.getMyUsedCoupons(uid);
                break;
            case EXPIRED:
                couponList = couponService.getMyExpiredCoupons(uid);
                break;
            default:
                throw new ParameterException(40001);
        }

        return CouponPureVo.getList(couponList);
    }

    /**
     * 我可用的优惠劵（带分类）
     * @return
     */
    @ScopeLevel()
    @GetMapping("/myself/available/with_category")
    public List<CouponCategoryVo> getUserCouponWithCategory() {
        User user = LocalUser.getUser();
        List<Coupon> coupons = couponService.getMyAvailableCoupons(user.getId());

        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }

        return coupons.stream()
                      .map(CouponCategoryVo::new)
                      .collect(Collectors.toList());
    }

}
