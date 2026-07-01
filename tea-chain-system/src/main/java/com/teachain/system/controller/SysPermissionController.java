package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysPermission;
import com.teachain.system.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class SysPermissionController {

    private final SysPermissionService sysPermissionService;

    @GetMapping
    public R<PageResult<SysPermission>> page(@RequestParam(defaultValue = "1") long current,
                                             @RequestParam(defaultValue = "10") long size) {
        Page<SysPermission> m = new Page<>(current, size);
        Page<SysPermission> page = sysPermissionService.page(m, new LambdaQueryWrapper<SysPermission>().orderByAsc(SysPermission::getSortOrder));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<SysPermission> getById(@PathVariable Long id) {
        return R.ok(sysPermissionService.getById(id));
    }

    @PostMapping
    public R<Boolean> save(@RequestBody SysPermission sysPermission) {
        return R.ok(sysPermissionService.save(sysPermission));
    }

    @PutMapping("/{id}")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysPermission sysPermission) {
        sysPermission.setId(id);
        return R.ok(sysPermissionService.updateById(sysPermission));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysPermissionService.removeById(id));
    }
}
