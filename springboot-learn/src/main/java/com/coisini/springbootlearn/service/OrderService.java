package com.coisini.springbootlearn.service;

import com.coisini.springbootlearn.dto.OrderDTO;
import com.coisini.springbootlearn.logic.OrderChecker;
import com.coisini.springbootlearn.model.Order;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @Description Order 接口
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
public interface OrderService {

    /**
     * 订单校验
     * @param uid 用户ID
     * @param orderDTO 订单参数
     * @return
     */
    OrderChecker isOk(Long uid, OrderDTO orderDTO);

    /**
     * 下单
     * @param uid 用户ID
     * @param orderDTO 订单参数
     * @param orderChecker 订单校验
     * @return
     */
    Long placeOrder(Long uid, OrderDTO orderDTO, OrderChecker orderChecker);

    /**
     * 查询待支付订单
     * @param page
     * @param size
     * @return
     */
    Page<Order> getUnpaid(Integer page, Integer size);

    /**
     * 根据状态获取订单
     * @param status 订单状态
     * @param page
     * @param size
     * @return
     */
    Page<Order> getByStatus(Integer status, Integer page, Integer size);

    /**
     * 获取订单详情
     * @param oid 订单ID
     * @return
     */
    Optional<Order> getOrderDetail(Long oid);

    /**
     * 更新支付ID
     * @param orderId 订单ID
     * @param prePayId 支付ID
     */
    void updateOrderPrepayId(Long orderId, String prePayId);

}
