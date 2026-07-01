package com.teachain.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.order.entity.OrderPayment;
import com.teachain.order.service.OrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-payments")
@RequiredArgsConstructor
public class OrderPaymentController {

    private final OrderPaymentService orderPaymentService;

    @GetMapping
    public R<PageResult<OrderPayment>> list(@RequestParam(defaultValue = "1") long current,
                                            @RequestParam(defaultValue = "10") long size) {
        Page<OrderPayment> page = orderPaymentService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<OrderPayment> detail(@PathVariable Long id) {
        return R.ok(orderPaymentService.getById(id));
    }

    @PostMapping
    public R<Void> add(@RequestBody OrderPayment orderPayment) {
        orderPaymentService.save(orderPayment);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody OrderPayment orderPayment) {
        orderPayment.setId(id);
        orderPaymentService.updateById(orderPayment);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        orderPaymentService.removeById(id);
        return R.ok();
    }
}
