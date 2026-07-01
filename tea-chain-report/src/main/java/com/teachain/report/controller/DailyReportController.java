package com.teachain.report.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 日报表 Controller
 */
@RestController
@RequestMapping("/api/franchise/reports/daily")
@RequiredArgsConstructor
public class DailyReportController {

    @GetMapping
    public R<Map<String, Object>> dailyReport(@RequestParam String date) {
        return R.ok(Map.of("date", date, "totalAmount", 0, "orderCount", 0));
    }
}
