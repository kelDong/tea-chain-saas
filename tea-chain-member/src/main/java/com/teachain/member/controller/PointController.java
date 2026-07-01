package com.teachain.member.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 积分管理 Controller
 */
@RestController
@RequestMapping("/api/franchise/points")
@RequiredArgsConstructor
public class PointController {

    @GetMapping("/balance")
    public R<Map<String, Integer>> balance() {
        return R.ok(Map.of("points", 0));
    }
}
