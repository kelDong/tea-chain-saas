package com.teachain.applet.controller;

import com.teachain.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/applet/pay")
@RequiredArgsConstructor
public class AppletPayController {

    @PostMapping("/unified-order")
    public R<Map<String, Object>> unifiedOrder(@RequestBody Map<String, Object> params) {
        try {
            String orderId = (String) params.get("orderId");
            BigDecimal totalAmount = new BigDecimal(params.get("totalAmount").toString());
            String body = (String) params.get("body");

            String prepayId = "prepay_" + System.currentTimeMillis();

            Map<String, Object> payInfo = new HashMap<>();
            payInfo.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));
            payInfo.put("package", "prepay_id=" + prepayId);
            payInfo.put("signType", "RSA");
            payInfo.put("paySign", "mock_pay_sign");

            log.info("创建支付订单成功: orderId={}, prepayId={}", orderId, prepayId);
            return R.ok(payInfo);

        } catch (Exception e) {
            log.error("创建支付订单失败", e);
            return R.fail("支付失败: " + e.getMessage());
        }
    }

    @PostMapping("/notify")
    public String notify(@RequestBody String xmlData) {
        try {
            log.info("支付成功回调: {}", xmlData);
            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        } catch (Exception e) {
            log.error("支付回调处理失败", e);
            return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";
        }
    }
}
