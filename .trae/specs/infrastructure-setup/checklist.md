# 茶饮甜品连锁加盟 SaaS 管理系统 - 基础架构搭建验证清单

## 架构完整性验证
- [ ] 多租户架构：租户拦截器配置正确，SQL 自动追加 tenant_id 条件
- [ ] 总部跨租户查询：TenantContextHolder.ignoreTenant() 功能正常
- [ ] JWT 认证：登录、刷新、登出流程完整
- [ ] Token 黑名单：登出后 token 失效
- [ ] RBAC 权限：角色权限规则正确执行
- [ ] 数据权限：门店级隔离实现

## 基础设施集成验证
- [ ] Redis 缓存：RedisTemplate 配置正确，缓存注解可用
- [ ] RabbitMQ：消息生产/消费正常
- [ ] MinIO：文件上传/下载正常
- [ ] 微信小程序：登录接口正常
- [ ] 微信支付：支付流程正常

## 稳定性验证
- [ ] 统一异常处理：全局异常捕获正常
- [ ] 日志体系：请求日志记录完整
- [ ] 异步任务：定时任务和异步方法正常
- [ ] API 文档：Swagger UI 可访问，文档完整

## 安全性验证
- [ ] 密码加密：BCrypt 加密正确
- [ ] JWT 签名：token 校验正常
- [ ] CORS 配置：跨域访问控制正确
- [ ] 接口权限：未授权访问被拒绝

## 编译构建验证
- [ ] Maven 编译：mvn clean compile 成功
- [ ] Maven 打包：mvn install 成功
- [ ] 测试编译：mvn test-compile 成功
