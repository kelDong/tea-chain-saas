# 茶饮甜品连锁加盟 SaaS 管理系统 - 基础架构搭建 PRD

## Overview
- **Summary**: 完成茶饮甜品连锁加盟 SaaS 管理系统的基础架构搭建，包括多租户架构、安全认证、消息队列、文件存储等核心基础设施
- **Purpose**: 为业务模块提供稳定、可扩展的技术底座，支持总部、加盟商、门店、顾客四类角色的业务运营
- **Target Users**: 系统管理员、加盟商、门店员工、小程序顾客

## Goals
- [x] 完善多租户架构，实现数据隔离
- [x] 实现 JWT 认证与权限控制
- [x] 集成 Redis 缓存
- [x] 集成 SpringDoc OpenAPI 文档
- [ ] 集成 RabbitMQ 消息队列
- [ ] 集成 MinIO 文件存储
- [ ] 实现数据权限控制（门店级隔离）
- [ ] 完善异常处理与日志体系
- [ ] 实现异步任务处理
- [ ] 集成微信小程序登录与支付

## Non-Goals (Out of Scope)
- 前端页面开发
- 具体业务逻辑实现（如订单流程、会员积分等）
- 第三方服务集成（短信、地图等）
- 数据库初始化脚本（schema.sql）

## Background & Context
- 项目基于 Spring Boot 3.5.5 + MyBatis-Plus 3.5.15
- 已完成基础模块划分（9个Maven模块）
- 已有基础配置：MyBatis-Plus、Spring Security、Redis、OpenAPI
- 已配置 Git 版本控制，远端仓库：https://github.com/kelDong/tea-chain-saas.git

## Functional Requirements
- **FR-1**: 多租户数据隔离 - 通过 tenant_id 自动过滤数据，总部可跨租户查询
- **FR-2**: JWT 认证体系 - 支持登录、token刷新、登出、黑名单机制
- **FR-3**: 权限控制 - 基于角色的访问控制（RBAC），支持门店级数据权限
- **FR-4**: 消息队列 - 异步处理订单通知、库存更新等
- **FR-5**: 文件存储 - 商品图片、门店照片等文件的上传与管理
- **FR-6**: 统一异常处理 - 全局异常捕获与统一响应格式
- **FR-7**: 微信小程序 - 小程序登录、支付集成
- **FR-8**: 异步任务 - 定时任务调度与异步执行

## Non-Functional Requirements
- **NFR-1**: 安全性 - JWT 签名、密码 BCrypt 加密、XSS/CSRF 防护
- **NFR-2**: 可扩展性 - 模块化设计，支持水平扩展
- **NFR-3**: 可维护性 - 规范的代码结构、完善的文档
- **NFR-4**: 性能 - Redis 缓存热点数据，消息队列削峰填谷

## Constraints
- **Technical**: Java 21, Spring Boot 3.5.5, MyBatis-Plus 3.5.15
- **Business**: 多租户架构，总部/加盟商/门店三级权限
- **Dependencies**: Redis, RabbitMQ, MinIO, MySQL

## Assumptions
- MySQL 数据库已就绪（库名：tea_chain_saas）
- Redis 服务已启动（localhost:6379）
- RabbitMQ 服务已启动（localhost:5672）
- MinIO 服务已启动（localhost:9000）

## Acceptance Criteria

### AC-1: 多租户架构验证
- **Given**: 数据库表包含 tenant_id 字段，租户拦截器已配置
- **When**: 加盟商用户发起查询请求
- **Then**: SQL 自动追加 `WHERE tenant_id = ?` 条件，只能看到自己租户的数据
- **Verification**: `programmatic`

### AC-2: JWT 认证验证
- **Given**: 用户已登录获取 accessToken
- **When**: 请求携带有效 token
- **Then**: 请求正常处理；token 过期返回 401；无效 token 返回 401
- **Verification**: `programmatic`

### AC-3: 权限控制验证
- **Given**: 不同角色用户（HQ_ADMIN/FRANCHISEE/STORE_STAFF/CUSTOMER）
- **When**: 访问不同权限接口
- **Then**: 角色匹配正常访问，不匹配返回 403
- **Verification**: `programmatic`

### AC-4: Redis 缓存验证
- **Given**: Redis 配置已完成
- **When**: 查询热点数据
- **Then**: 首次查询走数据库，后续查询走缓存
- **Verification**: `programmatic`

### AC-5: 消息队列验证
- **Given**: RabbitMQ 配置已完成
- **When**: 发送消息到队列
- **Then**: 消费者成功接收并处理消息
- **Verification**: `programmatic`

### AC-6: 文件存储验证
- **Given**: MinIO 配置已完成
- **When**: 上传文件
- **Then**: 文件成功存储，返回可访问 URL
- **Verification**: `programmatic`

### AC-7: 微信登录验证
- **Given**: 小程序配置已完成
- **When**: 小程序调用登录接口
- **Then**: 成功获取微信用户信息并创建/关联会员
- **Verification**: `programmatic`

## Open Questions
- [ ] Redis 是否需要配置哨兵模式？
- [ ] RabbitMQ 是否需要配置延迟队列？
- [ ] MinIO 是否需要配置多租户存储桶？
