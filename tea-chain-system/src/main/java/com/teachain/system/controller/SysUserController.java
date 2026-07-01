package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.SysUser;
import com.teachain.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping
    public R<PageResult<SysUser>> page(@RequestParam(defaultValue = "1") long current,
                                       @RequestParam(defaultValue = "10") long size) {
        Page<SysUser> m = new Page<>(current, size);
        Page<SysUser> page = sysUserService.page(m, new LambdaQueryWrapper<SysUser>().orderByDesc(SysUser::getCreateTime));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<SysUser> getById(@PathVariable Long id) {
        return R.ok(sysUserService.getById(id));
    }

    @PostMapping
    public R<Boolean> save(@RequestBody SysUser sysUser) {
        return R.ok(sysUserService.save(sysUser));
    }

    @PutMapping("/{id}")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody SysUser sysUser) {
        sysUser.setId(id);
        return R.ok(sysUserService.updateById(sysUser));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysUserService.removeById(id));
    }
}
