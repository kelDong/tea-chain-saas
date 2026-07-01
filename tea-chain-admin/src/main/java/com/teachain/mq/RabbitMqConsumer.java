package com.teachain.mq;

import com.teachain.common.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqConsumer {

    @RabbitListener(queues = RabbitMqConfig.ORDER_NOTIFY_QUEUE)
    public void handleOrderNotify(String message) {
        log.info("接收到订单通知消息: {}", message);
    }

    @RabbitListener(queues = RabbitMqConfig.INVENTORY_UPDATE_QUEUE)
    public void handleInventoryUpdate(String message) {
        log.info("接收到库存更新消息: {}", message);
    }
}
