package com.teachain.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.member.entity.MemberPointLog;
import com.teachain.member.service.MemberPointLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member-point-logs")
@RequiredArgsConstructor
public class MemberPointLogController {

    private final MemberPointLogService memberPointLogService;

    @GetMapping
    public R<PageResult<MemberPointLog>> list(@RequestParam(defaultValue = "1") long current,
                                             @RequestParam(defaultValue = "10") long size) {
        Page<MemberPointLog> page = memberPointLogService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<MemberPointLog> detail(@PathVariable Long id) {
        return R.ok(memberPointLogService.getById(id));
    }

    @PostMapping
    public R<Void> add(@RequestBody MemberPointLog memberPointLog) {
        memberPointLogService.save(memberPointLog);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody MemberPointLog memberPointLog) {
        memberPointLog.setId(id);
        memberPointLogService.updateById(memberPointLog);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        memberPointLogService.removeById(id);
        return R.ok();
    }
}
