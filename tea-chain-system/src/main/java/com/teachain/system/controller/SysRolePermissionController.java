package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysRolePermission;
import com.teachain.system.service.SysRolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role-permissions")
@RequiredArgsConstructor
public class SysRolePermissionController {

    private final SysRolePermissionService sysRolePermissionService;

    @GetMapping
    public R<PageResult<SysRolePermission>> page(@RequestParam(defaultValue = "1") long current,
                                                  @RequestParam(defaultValue = "10") long size) {
        Page<SysRolePermission> m = new Page<>(current, size);
        Page<SysRolePermission> page = sysRolePermissionService.page(m, new LambdaQueryWrapper<SysRolePermission>().orderByDesc(SysRolePermission::getCreateTime));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<SysRolePermission> getById(@PathVariable Long id) {
        return R.ok(sysRolePermissionService.getById(id));
    }

    @PostMapping
    public R<Boolean> save(@RequestBody SysRolePermission sysRolePermission) {
        return R.ok(sysRolePermissionService.save(sysRolePermission));
    }

    @PutMapping("/{id}")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysRolePermission sysRolePermission) {
        sysRolePermission.setId(id);
        return R.ok(sysRolePermissionService.updateById(sysRolePermission));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysRolePermissionService.removeById(id));
    }
}
