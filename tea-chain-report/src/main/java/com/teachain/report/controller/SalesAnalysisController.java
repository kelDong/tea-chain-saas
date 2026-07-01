package com.teachain.report.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 销售分析 Controller
 */
@RestController
@RequestMapping("/api/franchise/reports/sales")
@RequiredArgsConstructor
public class SalesAnalysisController {

    @GetMapping("/trend")
    public R<List<Map<String, Object>>> salesTrend(@RequestParam String startDate,
                                                    @RequestParam String endDate) {
        return R.ok(List.of());
    }
}
