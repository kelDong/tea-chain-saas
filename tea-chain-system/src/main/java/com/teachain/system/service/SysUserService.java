package com.teachain.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teachain.system.entity.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);

    List<String> getUserRoles(Long userId);

    List<String> getUserPermissions(Long userId);
}
