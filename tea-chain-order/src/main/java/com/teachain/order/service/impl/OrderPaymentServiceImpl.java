package com.teachain.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.order.entity.OrderPayment;
import com.teachain.order.mapper.OrderPaymentMapper;
import com.teachain.order.service.OrderPaymentService;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentServiceImpl extends ServiceImpl<OrderPaymentMapper, OrderPayment> implements OrderPaymentService {
}
