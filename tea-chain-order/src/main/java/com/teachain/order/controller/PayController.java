package com.teachain.order.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 支付管理 Controller
 */
@RestController
@RequestMapping("/api/store/pay")
@RequiredArgsConstructor
public class PayController {

    @PostMapping("/refund")
    public R<Void> refund(@RequestBody Map<String, Object> params) {
        return R.ok();
    }
}
