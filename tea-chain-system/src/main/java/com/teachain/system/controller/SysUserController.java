package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysUser;
import com.teachain.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class SysUserController {

    private final SysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAnyRole('HQ_ADMIN', 'FRANCHISEE')")
    public R<PageResult<SysUser>> page(@RequestParam(defaultValue = "1") long current,
                                       @RequestParam(defaultValue = "10") long size) {
        Page<SysUser> m = new Page<>(current, size);
        Page<SysUser> page = sysUserService.page(m, new LambdaQueryWrapper<SysUser>().orderByDesc(SysUser::getCreateTime));
        page.getRecords().forEach(user -> user.setPassword(null));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HQ_ADMIN', 'FRANCHISEE')")
    public R<Map<String, Object>> getById(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        if (user != null) {
            user.setPassword(null);
        }
        return R.ok(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> save(@RequestBody SysUser sysUser) {
        if (sysUser.getPassword() != null && !sysUser.getPassword().isEmpty()) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        return R.ok(sysUserService.save(sysUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysUser sysUser) {
        sysUser.setId(id);
        if (sysUser.getPassword() != null && !sysUser.getPassword().isEmpty()) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        } else {
            SysUser existing = sysUserService.getById(id);
            if (existing != null) {
                sysUser.setPassword(existing.getPassword());
            }
        }
        return R.ok(sysUserService.updateById(sysUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HQ_ADMIN')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysUserService.removeById(id));
    }
}
