package com.coisini.springbootlearn.vo;

import com.coisini.springbootlearn.model.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import java.util.Date;

/**
 * @Description Order Pure Vo
 * @author coisini
 * @date Aug 22, 2021
 * @Version 1.0
 */
@Getter
@Setter
public class OrderPureVo extends Order {

    /**
     * 订单时效
     */
    private Long period;
    private Date createTime;

    public OrderPureVo(Order order, Long period) {
        BeanUtils.copyProperties(order, this);
        this.period = period;
    }
}
