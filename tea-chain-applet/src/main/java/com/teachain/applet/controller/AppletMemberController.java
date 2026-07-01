package com.teachain.applet.controller;

import com.teachain.common.result.R;
import com.teachain.member.entity.Member;
import com.teachain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序会员接口
 */
@RestController
@RequestMapping("/api/applet/member")
@RequiredArgsConstructor
public class AppletMemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public R<Member> info() {
        return R.ok(new Member());
    }
}
