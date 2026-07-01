package com.teachain.common.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String ORDER_EXCHANGE = "order_exchange";
    public static final String ORDER_NOTIFY_QUEUE = "order_notify_queue";
    public static final String ORDER_NOTIFY_ROUTING_KEY = "order.notify";

    public static final String INVENTORY_EXCHANGE = "inventory_exchange";
    public static final String INVENTORY_UPDATE_QUEUE = "inventory_update_queue";
    public static final String INVENTORY_UPDATE_ROUTING_KEY = "inventory.update";

    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.topicExchange(ORDER_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue orderNotifyQueue() {
        return QueueBuilder.durable(ORDER_NOTIFY_QUEUE).build();
    }

    @Bean
    public Binding orderNotifyBinding() {
        return BindingBuilder.bind(orderNotifyQueue())
                .to(orderExchange())
                .with(ORDER_NOTIFY_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Exchange inventoryExchange() {
        return ExchangeBuilder.topicExchange(INVENTORY_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue inventoryUpdateQueue() {
        return QueueBuilder.durable(INVENTORY_UPDATE_QUEUE).build();
    }

    @Bean
    public Binding inventoryUpdateBinding() {
        return BindingBuilder.bind(inventoryUpdateQueue())
                .to(inventoryExchange())
                .with(INVENTORY_UPDATE_ROUTING_KEY)
                .noargs();
    }
}
