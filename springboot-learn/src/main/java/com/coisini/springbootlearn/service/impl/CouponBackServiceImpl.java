package com.coisini.springbootlearn.service.impl;

import com.coisini.springbootlearn.bo.OrderMessageBo;
import com.coisini.springbootlearn.core.enumeration.OrderStatus;
import com.coisini.springbootlearn.exception.http.ServerErrorException;
import com.coisini.springbootlearn.model.Order;
import com.coisini.springbootlearn.repository.OrderRepository;
import com.coisini.springbootlearn.repository.UserCouponRepository;
import com.coisini.springbootlearn.service.CouponBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * @Description 优惠劵返还实现类
 * @author coisini
 * @date Aug 25, 2021
 * @Version 1.0
 */
@Service
public class CouponBackServiceImpl implements CouponBackService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    /**
     * 优惠劵归还
     * @param bo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void returnBack(OrderMessageBo bo) {
        Long couponId = bo.getCouponId();
        Long uid = bo.getUserId();
        Long orderId = bo.getOrderId();

        if (couponId == -1) {
            return;
        }

        Optional<Order> optional = orderRepository.findFirstByUserIdAndId(uid, orderId);
        Order order = optional.orElseThrow(() ->new ServerErrorException(9999));

        if (order.getStatusEnum().equals(OrderStatus.UNPAID)
                || order.getStatusEnum().equals(OrderStatus.CANCELED)) {
            userCouponRepository.returnBack(couponId, uid);
        }
    }
}
