package com.teachain.applet.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 小程序支付接口
 */
@RestController
@RequestMapping("/api/applet/pay")
@RequiredArgsConstructor
public class AppletPayController {

    @PostMapping("/unified-order")
    public R<Map<String, String>> unifiedOrder(@RequestBody Map<String, Object> params) {
        return R.ok(Map.of("prepayId", "mock_prepay_id"));
    }
}
