package com.teachain.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.product.entity.MaterialSupplier;
import com.teachain.product.service.MaterialSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/material-suppliers")
@RequiredArgsConstructor
public class MaterialSupplierController {

    private final MaterialSupplierService materialSupplierService;

    @GetMapping
    public R<PageResult<MaterialSupplier>> page(@RequestParam(defaultValue = "1") long current,
                                                @RequestParam(defaultValue = "10") long size) {
        IPage<MaterialSupplier> page = materialSupplierService.page(new Page<>(current, size));
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<MaterialSupplier> getById(@PathVariable Long id) {
        return R.ok(materialSupplierService.getById(id));
    }

    @PostMapping
    public R<Void> save(@RequestBody MaterialSupplier materialSupplier) {
        materialSupplierService.save(materialSupplier);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody MaterialSupplier materialSupplier) {
        materialSupplier.setId(id);
        materialSupplierService.updateById(materialSupplier);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        materialSupplierService.removeById(id);
        return R.ok();
    }
}
