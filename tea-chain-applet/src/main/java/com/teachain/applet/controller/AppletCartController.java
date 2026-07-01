package com.teachain.applet.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 小程序购物车接口
 */
@RestController
@RequestMapping("/api/applet/cart")
@RequiredArgsConstructor
public class AppletCartController {

    @GetMapping
    public R<List<Map<String, Object>>> list() {
        return R.ok(List.of());
    }

    @PostMapping
    public R<Void> add(@RequestBody Map<String, Object> item) {
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        return R.ok();
    }
}
