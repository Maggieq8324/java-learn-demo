package com.coisini.springbootlearn.core.listener;

import com.coisini.springbootlearn.bo.OrderMessageBo;
import com.coisini.springbootlearn.service.CouponBackService;
import com.coisini.springbootlearn.service.impl.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @Description Redis 事件广播监听器
 *  Topic是消息发布(Pub)者和订阅(Sub)者之间的传输中介
 * @author coisini
 * @date
 * @Version 1.0
 */
public class TopicMessageListener implements MessageListener {

    @Autowired
    private OrderCancelService orderCancelService;

    @Autowired
    private CouponBackService couponBackService;

    /**
     * Redis 事件监听回调
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        String expiredKey = new String(body);
        String topic = new String(channel);

        OrderMessageBo messageBo = new OrderMessageBo(expiredKey);

        /**
         * 订单取消
         */
        orderCancelService.cancel(messageBo);

        /**
         * 优惠劵归还
         */
        couponBackService.returnBack(messageBo);
    }
}
