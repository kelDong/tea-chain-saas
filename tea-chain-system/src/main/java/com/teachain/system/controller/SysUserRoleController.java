package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysUserRole;
import com.teachain.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
public class SysUserRoleController {

    private final SysUserRoleService sysUserRoleService;

    @GetMapping
    public R<PageResult<SysUserRole>> page(@RequestParam(defaultValue = "1") long current,
                                           @RequestParam(defaultValue = "10") long size) {
        Page<SysUserRole> m = new Page<>(current, size);
        Page<SysUserRole> page = sysUserRoleService.page(m, new LambdaQueryWrapper<SysUserRole>().orderByDesc(SysUserRole::getCreateTime));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<SysUserRole> getById(@PathVariable Long id) {
        return R.ok(sysUserRoleService.getById(id));
    }

    @PostMapping
    public R<Boolean> save(@RequestBody SysUserRole sysUserRole) {
        return R.ok(sysUserRoleService.save(sysUserRole));
    }

    @PutMapping("/{id}")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysUserRole sysUserRole) {
        sysUserRole.setId(id);
        return R.ok(sysUserRoleService.updateById(sysUserRole));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysUserRoleService.removeById(id));
    }
}
