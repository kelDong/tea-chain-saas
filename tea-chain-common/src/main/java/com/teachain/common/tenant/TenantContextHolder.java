package com.teachain.common.tenant;

public class TenantContextHolder {

    private static final ThreadLocal<TenantContext> CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> IGNORE_TENANT = ThreadLocal.withInitial(() -> false);

    public static void set(Long tenantId, Long userId, Long storeId, String username) {
        TenantContext ctx = new TenantContext();
        ctx.tenantId = tenantId;
        ctx.userId = userId;
        ctx.storeId = storeId;
        ctx.username = username;
        CONTEXT.set(ctx);
    }

    public static TenantContext get() {
        return CONTEXT.get();
    }

    public static Long getTenantId() {
        TenantContext ctx = CONTEXT.get();
        return ctx == null ? null : ctx.tenantId;
    }

    public static void setTenantId(Long tenantId) {
        TenantContext ctx = CONTEXT.get();
        if (ctx == null) {
            ctx = new TenantContext();
            CONTEXT.set(ctx);
        }
        ctx.tenantId = tenantId;
    }

    public static Long getUserId() {
        TenantContext ctx = CONTEXT.get();
        return ctx == null ? null : ctx.userId;
    }

    public static Long getStoreId() {
        TenantContext ctx = CONTEXT.get();
        return ctx == null ? null : ctx.storeId;
    }

    public static boolean isIgnoreTenant() {
        return Boolean.TRUE.equals(IGNORE_TENANT.get());
    }

    public static void setIgnoreTenant(boolean ignore) {
        IGNORE_TENANT.set(ignore);
    }

    public static void clear() {
        CONTEXT.remove();
        IGNORE_TENANT.remove();
    }

    public static class TenantContext {
        public Long tenantId;
        public Long userId;
        public Long storeId;
        public String username;
    }
}
