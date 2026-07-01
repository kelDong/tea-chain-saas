package com.teachain.applet.controller;

import com.teachain.common.result.R;
import com.teachain.common.utils.JwtUtils;
import com.teachain.member.entity.Member;
import com.teachain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/applet")
@RequiredArgsConstructor
public class AppletLoginController {

    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @PostMapping("/wx-login")
    public R<Map<String, Object>> wxLogin(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        try {
            String openid = "mock_openid_" + System.currentTimeMillis();

            Member member = memberService.lambdaQuery()
                    .eq(Member::getOpenid, openid)
                    .one();

            if (member == null) {
                member = new Member();
                member.setOpenid(openid);
                member.setNickname("微信用户");
                memberService.save(member);
            }

            String accessToken = jwtUtils.generateAccessToken(
                    member.getId(), null, null, member.getNickname(), null);
            String refreshToken = jwtUtils.generateRefreshToken(member.getId());

            Map<String, Object> result = new HashMap<>();
            result.put("accessToken", accessToken);
            result.put("refreshToken", refreshToken);
            result.put("member", member);

            log.info("微信小程序登录成功: openid={}, memberId={}", openid, member.getId());
            return R.ok(result);

        } catch (Exception e) {
            log.error("微信登录失败", e);
            return R.fail("微信登录失败");
        }
    }
}
