package com.teachain.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.store.entity.PurchaseOrderItem;
import com.teachain.store.service.PurchaseOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-order-items")
@RequiredArgsConstructor
public class PurchaseOrderItemController {

    private final PurchaseOrderItemService purchaseOrderItemService;

    @GetMapping("/page")
    public R<PageResult<PurchaseOrderItem>> page(@RequestParam(defaultValue = "1") long current,
                                                 @RequestParam(defaultValue = "10") long size) {
        Page<PurchaseOrderItem> page = purchaseOrderItemService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<PurchaseOrderItem> detail(@PathVariable Long id) {
        return R.ok(purchaseOrderItemService.getById(id));
    }

    @PostMapping
    public R<Boolean> add(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        return R.ok(purchaseOrderItemService.save(purchaseOrderItem));
    }

    @PutMapping
    public R<Boolean> update(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        return R.ok(purchaseOrderItemService.updateById(purchaseOrderItem));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(purchaseOrderItemService.removeById(id));
    }
}
