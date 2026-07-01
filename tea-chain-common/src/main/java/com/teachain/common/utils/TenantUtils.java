package com.teachain.common.utils;

import com.teachain.common.tenant.TenantContextHolder;

/**
 * 租户工具类
 */
public class TenantUtils {

    /**
     * 忽略租户过滤执行逻辑
     */
    public static void ignoreTenant(Runnable runnable) {
        boolean ignored = TenantContextHolder.isIgnoreTenant();
        try {
            TenantContextHolder.setIgnoreTenant(true);
            runnable.run();
        } finally {
            TenantContextHolder.setIgnoreTenant(ignored);
        }
    }
}
