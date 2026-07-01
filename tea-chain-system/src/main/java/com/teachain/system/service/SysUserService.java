package com.teachain.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teachain.system.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);
}
