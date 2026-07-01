package com.teachain.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.store.entity.Store;
import com.teachain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/page")
    public R<PageResult<Store>> page(@RequestParam(defaultValue = "1") long current,
                                     @RequestParam(defaultValue = "10") long size) {
        Page<Store> page = storeService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<Store> detail(@PathVariable Long id) {
        return R.ok(storeService.getById(id));
    }

    @PostMapping
    public R<Boolean> add(@RequestBody Store store) {
        return R.ok(storeService.save(store));
    }

    @PutMapping
    public R<Boolean> update(@RequestBody Store store) {
        return R.ok(storeService.updateById(store));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(storeService.removeById(id));
    }
}
