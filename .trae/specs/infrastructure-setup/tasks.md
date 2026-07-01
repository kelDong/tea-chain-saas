# 茶饮甜品连锁加盟 SaaS 管理系统 - 基础架构搭建任务清单

## [x] Task 1: 完善多租户架构
- **Priority**: high
- **Depends On**: None
- **Description**: 
  - 验证租户拦截器配置正确
  - 确保 BaseEntity 包含 tenant_id 字段
  - 实现 TenantContextHolder.ignoreTenant() 功能
- **Acceptance Criteria Addressed**: AC-1
- **Test Requirements**:
  - `programmatic` TR-1.1: 加盟商查询时 SQL 自动追加 tenant_id 条件
  - `programmatic` TR-1.2: 总部调用 ignoreTenant() 可查询所有租户数据

## [x] Task 2: 完善 JWT 认证体系
- **Priority**: high
- **Depends On**: None
- **Description**: 
  - 实现登录接口（POST /api/auth/login）
  - 实现 Token 刷新接口（POST /api/auth/refresh-token）
  - 实现登出接口（POST /api/auth/logout）
  - 实现 Token 黑名单机制
- **Acceptance Criteria Addressed**: AC-2
- **Test Requirements**:
  - `programmatic` TR-2.1: 登录返回 accessToken 和 refreshToken
  - `programmatic` TR-2.2: 无效 token 返回 401
  - `programmatic` TR-2.3: 过期 token 返回 401 并提示刷新
  - `programmatic` TR-2.4: 登出后 token 加入黑名单

## [x] Task 3: 完善权限控制（RBAC）
- **Priority**: high
- **Depends On**: Task 2
- **Description**: 
  - 实现角色权限查询
  - 实现数据权限（门店级隔离）
  - 完善 SecurityConfig 权限规则
- **Acceptance Criteria Addressed**: AC-3
- **Test Requirements**:
  - `programmatic` TR-3.1: HQ_ADMIN 可访问所有接口
  - `programmatic` TR-3.2: FRANCHISEE 可访问加盟商接口
  - `programmatic` TR-3.3: STORE_STAFF 仅可访问所在门店数据
  - `programmatic` TR-3.4: 无权限访问返回 403

## [x] Task 4: Redis 缓存集成
- **Priority**: high
- **Depends On**: None
- **Description**: 
  - 完善 RedisConfig 配置
  - 实现 RedisTemplate 序列化配置
  - 实现缓存注解（@Cacheable 等）
- **Acceptance Criteria Addressed**: AC-4
- **Test Requirements**:
  - `programmatic` TR-4.1: RedisTemplate 可正常操作
  - `programmatic` TR-4.2: 使用 @Cacheable 注解的数据可被缓存
  - `programmatic` TR-4.3: 缓存过期后自动刷新

## [ ] Task 5: RabbitMQ 消息队列集成
- **Priority**: medium
- **Depends On**: None
- **Description**: 
  - 在 common 模块添加 RabbitMQ 配置
  - 实现消息生产者
  - 实现消息消费者
  - 配置队列、交换机、绑定关系
- **Acceptance Criteria Addressed**: AC-5
- **Test Requirements**:
  - `programmatic` TR-5.1: 消息成功发送到队列
  - `programmatic` TR-5.2: 消费者成功接收并处理消息
  - `programmatic` TR-5.3: 消息消费失败可重试

## [ ] Task 6: MinIO 文件存储集成
- **Priority**: medium
- **Depends On**: None
- **Description**: 
  - 添加 MinIO 依赖
  - 配置 MinIO 客户端
  - 实现文件上传接口
  - 实现文件下载/预览接口
- **Acceptance Criteria Addressed**: AC-6
- **Test Requirements**:
  - `programmatic` TR-6.1: 文件成功上传到 MinIO
  - `programmatic` TR-6.2: 返回可访问的文件 URL
  - `programmatic` TR-6.3: 文件可正常下载

## [ ] Task 7: 微信小程序登录集成
- **Priority**: medium
- **Depends On**: Task 2
- **Description**: 
  - 配置微信小程序参数
  - 实现微信登录接口（POST /api/applet/wx-login）
  - 实现微信用户与会员关联
- **Acceptance Criteria Addressed**: AC-7
- **Test Requirements**:
  - `programmatic` TR-7.1: 传入 code 可获取 openid
  - `programmatic` TR-7.2: 首次登录自动创建会员
  - `programmatic` TR-7.3: 返回会员信息和 token

## [ ] Task 8: 微信支付集成
- **Priority**: medium
- **Depends On**: Task 7
- **Description**: 
  - 配置微信支付参数
  - 实现统一下单接口
  - 实现支付回调处理
  - 实现退款接口
- **Acceptance Criteria Addressed**: AC-7
- **Test Requirements**:
  - `programmatic` TR-8.1: 成功生成支付订单
  - `programmatic` TR-8.2: 支付回调正确处理
  - `programmatic` TR-8.3: 退款功能正常

## [ ] Task 9: 完善异常处理与日志体系
- **Priority**: medium
- **Depends On**: None
- **Description**: 
  - 完善 GlobalExceptionHandler
  - 添加业务异常枚举
  - 配置日志框架（Logback）
  - 实现请求日志拦截器
- **Test Requirements**:
  - `programmatic` TR-9.1: 业务异常返回统一格式
  - `programmatic` TR-9.2: 系统异常返回友好提示
  - `human-judgment` TR-9.3: 日志格式规范，包含请求信息

## [ ] Task 10: 异步任务处理
- **Priority**: low
- **Depends On**: None
- **Description**: 
  - 配置 @EnableAsync
  - 配置线程池
  - 实现定时任务（@Scheduled）
  - 实现异步方法（@Async）
- **Test Requirements**:
  - `programmatic` TR-10.1: 异步方法在单独线程执行
  - `programmatic` TR-10.2: 定时任务按配置时间执行
  - `human-judgment` TR-10.3: 线程池配置合理

## [ ] Task 11: API 文档完善
- **Priority**: low
- **Depends On**: None
- **Description**: 
  - 为所有 Controller 添加 OpenAPI 注解
  - 配置分组文档（admin/applet）
  - 完善接口描述和参数说明
- **Test Requirements**:
  - `human-judgment` TR-11.1: Swagger UI 可访问
  - `human-judgment` TR-11.2: 接口文档完整清晰

## [ ] Task 12: 数据权限控制（门店级）
- **Priority**: medium
- **Depends On**: Task 3
- **Description**: 
  - 实现数据权限注解
  - 实现数据权限拦截器
  - 支持 store_id 过滤
- **Test Requirements**:
  - `programmatic` TR-12.1: 门店员工只能看到所在门店数据
  - `programmatic` TR-12.2: 加盟商可看到旗下所有门店数据
