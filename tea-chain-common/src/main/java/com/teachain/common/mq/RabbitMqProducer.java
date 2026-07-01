package com.teachain.common.mq;

import com.teachain.common.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendOrderNotify(Long orderId, String message) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMqConfig.ORDER_EXCHANGE,
                    RabbitMqConfig.ORDER_NOTIFY_ROUTING_KEY,
                    message
            );
            log.info("发送订单通知消息: orderId={}", orderId);
        } catch (Exception e) {
            log.error("发送订单通知消息失败: orderId={}", orderId, e);
        }
    }

    public void sendInventoryUpdate(Long productId, Integer quantity) {
        try {
            String message = String.format("{\"productId\":%d,\"quantity\":%d}", productId, quantity);
            rabbitTemplate.convertAndSend(
                    RabbitMqConfig.INVENTORY_EXCHANGE,
                    RabbitMqConfig.INVENTORY_UPDATE_ROUTING_KEY,
                    message
            );
            log.info("发送库存更新消息: productId={}, quantity={}", productId, quantity);
        } catch (Exception e) {
            log.error("发送库存更新消息失败: productId={}", productId, e);
        }
    }
}
