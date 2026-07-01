package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysRole;
import com.teachain.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Validated
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HQ_ADMIN', 'FRANCHISEE')")
    public R<PageResult<SysRole>> page(@RequestParam(defaultValue = "1") long current,
                                      @RequestParam(defaultValue = "10") long size) {
        Page<SysRole> m = new Page<>(current, size);
        Page<SysRole> page = sysRoleService.page(m, new LambdaQueryWrapper<SysRole>().orderByAsc(SysRole::getSortOrder));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HQ_ADMIN', 'FRANCHISEE')")
    public R<SysRole> getById(@PathVariable Long id) {
        return R.ok(sysRoleService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> save(@RequestBody SysRole sysRole) {
        return R.ok(sysRoleService.save(sysRole));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysRole sysRole) {
        sysRole.setId(id);
        return R.ok(sysRoleService.updateById(sysRole));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysRoleService.removeById(id));
    }
}
