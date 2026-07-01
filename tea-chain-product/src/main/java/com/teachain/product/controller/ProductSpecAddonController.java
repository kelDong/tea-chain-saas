package com.teachain.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.product.entity.ProductSpecAddon;
import com.teachain.product.service.ProductSpecAddonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-addons")
@RequiredArgsConstructor
public class ProductSpecAddonController {

    private final ProductSpecAddonService productSpecAddonService;

    @GetMapping
    public R<PageResult<ProductSpecAddon>> page(@RequestParam(defaultValue = "1") long current,
                                                @RequestParam(defaultValue = "10") long size) {
        IPage<ProductSpecAddon> page = productSpecAddonService.page(new Page<>(current, size));
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<ProductSpecAddon> getById(@PathVariable Long id) {
        return R.ok(productSpecAddonService.getById(id));
    }

    @PostMapping
    public R<Void> save(@RequestBody ProductSpecAddon productSpecAddon) {
        productSpecAddonService.save(productSpecAddon);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody ProductSpecAddon productSpecAddon) {
        productSpecAddon.setId(id);
        productSpecAddonService.updateById(productSpecAddon);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        productSpecAddonService.removeById(id);
        return R.ok();
    }
}
