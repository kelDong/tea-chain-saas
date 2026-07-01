package com.teachain.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.member.entity.CouponTemplate;
import com.teachain.member.service.CouponTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon-templates")
@RequiredArgsConstructor
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

    @GetMapping
    public R<PageResult<CouponTemplate>> list(@RequestParam(defaultValue = "1") long current,
                                              @RequestParam(defaultValue = "10") long size) {
        Page<CouponTemplate> page = couponTemplateService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<CouponTemplate> detail(@PathVariable Long id) {
        return R.ok(couponTemplateService.getById(id));
    }

    @PostMapping
    public R<Void> add(@RequestBody CouponTemplate couponTemplate) {
        couponTemplateService.save(couponTemplate);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody CouponTemplate couponTemplate) {
        couponTemplate.setId(id);
        couponTemplateService.updateById(couponTemplate);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        couponTemplateService.removeById(id);
        return R.ok();
    }
}
