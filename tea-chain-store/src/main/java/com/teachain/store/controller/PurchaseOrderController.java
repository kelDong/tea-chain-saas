package com.teachain.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.store.entity.PurchaseOrder;
import com.teachain.store.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/page")
    public R<PageResult<PurchaseOrder>> page(@RequestParam(defaultValue = "1") long current,
                                             @RequestParam(defaultValue = "10") long size) {
        Page<PurchaseOrder> page = purchaseOrderService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<PurchaseOrder> detail(@PathVariable Long id) {
        return R.ok(purchaseOrderService.getById(id));
    }

    @PostMapping
    public R<Boolean> add(@RequestBody PurchaseOrder purchaseOrder) {
        return R.ok(purchaseOrderService.save(purchaseOrder));
    }

    @PutMapping
    public R<Boolean> update(@RequestBody PurchaseOrder purchaseOrder) {
        return R.ok(purchaseOrderService.updateById(purchaseOrder));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(purchaseOrderService.removeById(id));
    }
}
