package com.teachain.system.controller;

import com.teachain.common.exception.BizException;
import com.teachain.common.result.R;
import com.teachain.common.utils.JwtUtils;
import com.teachain.system.entity.SysUser;
import com.teachain.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) {
            throw new BizException(400, "用户名或密码不能为空");
        }
        SysUser user = sysUserService.getByUsername(username);
        if (user == null) {
            throw new BizException(401, "用户不存在");
        }
        if (user.getStatus() != null && user.getStatus() != 1) {
            throw new BizException(403, "账号已禁用");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BizException(401, "密码错误");
        }
        String token = jwtUtils.generate(user.getId(), user.getTenantId(), user.getStoreId(), user.getUsername());

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("realName", user.getRealName());
        userInfo.put("phone", user.getPhone());
        userInfo.put("email", user.getEmail());
        userInfo.put("avatar", user.getAvatar());
        userInfo.put("tenantId", user.getTenantId());
        userInfo.put("storeId", user.getStoreId());

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", userInfo);
        return R.ok(result);
    }
}
