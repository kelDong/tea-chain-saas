package com.teachain.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.system.entity.SysPermission;
import com.teachain.system.entity.SysRole;
import com.teachain.system.entity.SysRolePermission;
import com.teachain.system.entity.SysUser;
import com.teachain.system.entity.SysUserRole;
import com.teachain.system.mapper.SysUserMapper;
import com.teachain.system.service.SysPermissionService;
import com.teachain.system.service.SysRolePermissionService;
import com.teachain.system.service.SysRoleService;
import com.teachain.system.service.SysUserRoleService;
import com.teachain.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserRoleService sysUserRoleService;
    private final SysRoleService sysRoleService;
    private final SysRolePermissionService sysRolePermissionService;
    private final SysPermissionService sysPermissionService;

    @Override
    public SysUser getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        List<SysUserRole> userRoles = sysUserRoleService.list(
                new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId)
        );
        if (userRoles.isEmpty()) {
            return List.of();
        }
        List<Long> roleIds = userRoles.stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        return sysRoleService.listByIds(roleIds).stream()
                .map(r -> "ROLE_" + r.getRoleKey().toUpperCase())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getUserPermissions(Long userId) {
        List<SysUserRole> userRoles = sysUserRoleService.list(
                new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId)
        );
        if (userRoles.isEmpty()) {
            return List.of();
        }
        List<Long> roleIds = userRoles.stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());
        List<SysRolePermission> rolePermissions = sysRolePermissionService.list(
                new LambdaQueryWrapper<SysRolePermission>().in(SysRolePermission::getRoleId, roleIds)
        );
        if (rolePermissions.isEmpty()) {
            return List.of();
        }
        List<Long> permissionIds = rolePermissions.stream()
                .map(SysRolePermission::getPermissionId)
                .collect(Collectors.toList());
        return sysPermissionService.listByIds(permissionIds).stream()
                .map(SysPermission::getPermissionKey)
                .collect(Collectors.toList());
    }
}
