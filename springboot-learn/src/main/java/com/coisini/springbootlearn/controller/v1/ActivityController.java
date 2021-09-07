package com.coisini.springbootlearn.controller.v1;

import com.coisini.springbootlearn.exception.http.NotFoundException;
import com.coisini.springbootlearn.model.Activity;
import com.coisini.springbootlearn.service.ActivityService;
import com.coisini.springbootlearn.vo.ActivityCouponVo;
import com.coisini.springbootlearn.vo.ActivityPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Activity 控制器
 * @author coisini
 * @date Aug 20, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/name/{name}")
    public ActivityPureVo getHomeActivity(@PathVariable String name) {
        Activity activity = activityService.getByName(name);
        if (activity == null) {
            throw new NotFoundException(40001);
        }

        return new ActivityPureVo(activity);
    }

    @GetMapping("/name/{name}/with_coupon")
    public ActivityCouponVo getActivityWithCoupons(@PathVariable String name) {
        Activity activity = activityService.getByName(name);
        if (activity == null) {
            throw new NotFoundException(40001);
        }
        return new ActivityCouponVo(activity);
    }

}
