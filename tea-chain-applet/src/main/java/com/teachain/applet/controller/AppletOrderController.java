package com.teachain.applet.controller;

import com.teachain.common.result.R;
import com.teachain.order.entity.Order;
import com.teachain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序订单接口
 */
@RestController
@RequestMapping("/api/applet/orders")
@RequiredArgsConstructor
public class AppletOrderController {

    private final OrderService orderService;

    @GetMapping
    public R<List<Order>> list() {
        return R.ok(orderService.list());
    }

    @PostMapping
    public R<Void> create(@RequestBody Order order) {
        orderService.save(order);
        return R.ok();
    }
}
