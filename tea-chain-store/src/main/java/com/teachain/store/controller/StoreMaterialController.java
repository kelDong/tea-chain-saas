package com.teachain.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.store.entity.StoreMaterial;
import com.teachain.store.service.StoreMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store-materials")
@RequiredArgsConstructor
public class StoreMaterialController {

    private final StoreMaterialService storeMaterialService;

    @GetMapping("/page")
    public R<PageResult<StoreMaterial>> page(@RequestParam(defaultValue = "1") long current,
                                             @RequestParam(defaultValue = "10") long size) {
        Page<StoreMaterial> page = storeMaterialService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<StoreMaterial> detail(@PathVariable Long id) {
        return R.ok(storeMaterialService.getById(id));
    }

    @PostMapping
    public R<Boolean> add(@RequestBody StoreMaterial storeMaterial) {
        return R.ok(storeMaterialService.save(storeMaterial));
    }

    @PutMapping
    public R<Boolean> update(@RequestBody StoreMaterial storeMaterial) {
        return R.ok(storeMaterialService.updateById(storeMaterial));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(storeMaterialService.removeById(id));
    }
}
