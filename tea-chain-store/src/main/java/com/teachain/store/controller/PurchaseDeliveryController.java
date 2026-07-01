package com.teachain.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.store.entity.PurchaseDelivery;
import com.teachain.store.service.PurchaseDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-deliveries")
@RequiredArgsConstructor
public class PurchaseDeliveryController {

    private final PurchaseDeliveryService purchaseDeliveryService;

    @GetMapping("/page")
    public R<PageResult<PurchaseDelivery>> page(@RequestParam(defaultValue = "1") long current,
                                                 @RequestParam(defaultValue = "10") long size) {
        Page<PurchaseDelivery> page = purchaseDeliveryService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<PurchaseDelivery> detail(@PathVariable Long id) {
        return R.ok(purchaseDeliveryService.getById(id));
    }

    @PostMapping
    public R<Boolean> add(@RequestBody PurchaseDelivery purchaseDelivery) {
        return R.ok(purchaseDeliveryService.save(purchaseDelivery));
    }

    @PutMapping
    public R<Boolean> update(@RequestBody PurchaseDelivery purchaseDelivery) {
        return R.ok(purchaseDeliveryService.updateById(purchaseDelivery));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(purchaseDeliveryService.removeById(id));
    }
}
