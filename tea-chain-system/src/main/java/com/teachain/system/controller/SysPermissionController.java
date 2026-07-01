package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysPermission;
import com.teachain.system.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Validated
public class SysPermissionController {

    private final SysPermissionService sysPermissionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HQ_ADMIN', 'FRANCHISEE')")
    public R<PageResult<SysPermission>> page(@RequestParam(defaultValue = "1") long current,
                                             @RequestParam(defaultValue = "10") long size) {
        Page<SysPermission> m = new Page<>(current, size);
        Page<SysPermission> page = sysPermissionService.page(m, new LambdaQueryWrapper<SysPermission>().orderByAsc(SysPermission::getSortOrder));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HQ_ADMIN', 'FRANCHISEE')")
    public R<SysPermission> getById(@PathVariable Long id) {
        return R.ok(sysPermissionService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> save(@RequestBody SysPermission sysPermission) {
        return R.ok(sysPermissionService.save(sysPermission));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysPermission sysPermission) {
        sysPermission.setId(id);
        return R.ok(sysPermissionService.updateById(sysPermission));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysPermissionService.removeById(id));
    }
}
