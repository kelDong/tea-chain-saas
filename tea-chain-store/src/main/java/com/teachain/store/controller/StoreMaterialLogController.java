package com.teachain.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.store.entity.StoreMaterialLog;
import com.teachain.store.service.StoreMaterialLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store-material-logs")
@RequiredArgsConstructor
public class StoreMaterialLogController {

    private final StoreMaterialLogService storeMaterialLogService;

    @GetMapping("/page")
    public R<PageResult<StoreMaterialLog>> page(@RequestParam(defaultValue = "1") long current,
                                                 @RequestParam(defaultValue = "10") long size) {
        Page<StoreMaterialLog> page = storeMaterialLogService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<StoreMaterialLog> detail(@PathVariable Long id) {
        return R.ok(storeMaterialLogService.getById(id));
    }

    @PostMapping
    public R<Boolean> add(@RequestBody StoreMaterialLog storeMaterialLog) {
        return R.ok(storeMaterialLogService.save(storeMaterialLog));
    }

    @PutMapping
    public R<Boolean> update(@RequestBody StoreMaterialLog storeMaterialLog) {
        return R.ok(storeMaterialLogService.updateById(storeMaterialLog));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        return R.ok(storeMaterialLogService.removeById(id));
    }
}
