package com.teachain.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.product.entity.ProductSpec;
import com.teachain.product.service.ProductSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-specs")
@RequiredArgsConstructor
public class ProductSpecController {

    private final ProductSpecService productSpecService;

    @GetMapping
    public R<PageResult<ProductSpec>> page(@RequestParam(defaultValue = "1") long current,
                                           @RequestParam(defaultValue = "10") long size) {
        IPage<ProductSpec> page = productSpecService.page(new Page<>(current, size));
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<ProductSpec> getById(@PathVariable Long id) {
        return R.ok(productSpecService.getById(id));
    }

    @PostMapping
    public R<Void> save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody ProductSpec productSpec) {
        productSpec.setId(id);
        productSpecService.updateById(productSpec);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        productSpecService.removeById(id);
        return R.ok();
    }
}
