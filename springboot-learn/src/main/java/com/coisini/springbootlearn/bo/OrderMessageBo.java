package com.coisini.springbootlearn.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description Order Message Business Object
 * @author coisini
 * @date Aug 25, 2021
 * @Version 1.0
 */
@Getter
@Setter
public class OrderMessageBo {
    private Long orderId;
    private Long couponId;
    private Long userId;
    private String message;

    public OrderMessageBo(String message) {
        this.message = message;
        this.parseId(message);
    }

    private void parseId(String message) {
        String[] temp = message.split(",");
        this.userId = Long.valueOf(temp[0]);
        this.orderId = Long.valueOf(temp[1]);
        this.couponId = Long.valueOf(temp[2]);
    }

}
