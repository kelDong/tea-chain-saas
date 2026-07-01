package com.teachain.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.system.entity.Tenant;
import com.teachain.system.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public R<PageResult<Tenant>> page(@RequestParam(defaultValue = "1") long current,
                                      @RequestParam(defaultValue = "10") long size) {
        Page<Tenant> m = new Page<>(current, size);
        Page<Tenant> page = tenantService.page(m, new LambdaQueryWrapper<Tenant>().orderByDesc(Tenant::getCreateTime));
        return R.ok(PageResult.of(page.getTotal(), size, current, page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<Tenant> getById(@PathVariable Long id) {
        return R.ok(tenantService.getById(id));
    }

    @PostMapping
    public R<Boolean> save(@RequestBody Tenant tenant) {
        return R.ok(tenantService.save(tenant));
    }

    @PutMapping("/{id}")
    public R<Boolean> updateById(@PathVariable Long id, @RequestBody Tenant tenant) {
        tenant.setId(id);
        return R.ok(tenantService.updateById(tenant));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(tenantService.removeById(id));
    }
}
