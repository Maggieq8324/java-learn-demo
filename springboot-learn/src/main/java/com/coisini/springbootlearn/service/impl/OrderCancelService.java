package com.coisini.springbootlearn.service.impl;

import com.coisini.springbootlearn.bo.OrderMessageBo;

/**
 * @Description 订单取消接口
 * @author coisini
 * @date Aug 25, 2021
 * @Version 1.0
 */
public interface OrderCancelService {

    /**
     * 订单取消
     * @param messageBo
     */
    void cancel(OrderMessageBo messageBo);

}
