package com.teachain.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.member.entity.MemberLevel;
import com.teachain.member.service.MemberLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member-levels")
@RequiredArgsConstructor
public class MemberLevelController {

    private final MemberLevelService memberLevelService;

    @GetMapping
    public R<PageResult<MemberLevel>> list(@RequestParam(defaultValue = "1") long current,
                                           @RequestParam(defaultValue = "10") long size) {
        Page<MemberLevel> page = memberLevelService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<MemberLevel> detail(@PathVariable Long id) {
        return R.ok(memberLevelService.getById(id));
    }

    @PostMapping
    public R<Void> add(@RequestBody MemberLevel memberLevel) {
        memberLevelService.save(memberLevel);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody MemberLevel memberLevel) {
        memberLevel.setId(id);
        memberLevelService.updateById(memberLevel);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        memberLevelService.removeById(id);
        return R.ok();
    }
}
