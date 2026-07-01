package com.teachain.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * MyBatis-Plus 代码生成器（3.5.15 + Freemarker）
 * <p>
 * 使用前请确保：
 * 1. MySQL 数据库 tea_chain_saas 已创建
 * 2. 已执行 schema.sql 建表脚本
 * 3. 修改下方的 DB_URL / DB_USERNAME / DB_PASSWORD 为实际数据库连接信息
 * </p>
 */
public class CodeGenerator {

    // ==================== 数据库配置 ====================
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tea_chain_saas?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    // ==================== 项目路径配置 ====================
    private static final String PROJECT_PATH = System.getProperty("user.dir");

    private static final String SYSTEM_PATH  = PROJECT_PATH + "/tea-chain-system/src/main/java";
    private static final String PRODUCT_PATH = PROJECT_PATH + "/tea-chain-product/src/main/java";
    private static final String STORE_PATH   = PROJECT_PATH + "/tea-chain-store/src/main/java";
    private static final String ORDER_PATH   = PROJECT_PATH + "/tea-chain-order/src/main/java";
    private static final String MEMBER_PATH  = PROJECT_PATH + "/tea-chain-member/src/main/java";
    private static final String REPORT_PATH  = PROJECT_PATH + "/tea-chain-report/src/main/java";

    private static final String MAPPER_XML_PATH = PROJECT_PATH + "/tea-chain-admin/src/main/resources/mapper";

    // ==================== 包名配置 ====================
    private static final String PARENT_PKG = "com.teachain";

    public static void main(String[] args) {
        generateSystemModule();
        generateProductModule();
        generateStoreModule();
        generateOrderModule();
        generateMemberModule();
        generateReportModule();
        System.out.println("========== 所有模块代码生成完毕 ==========");
    }

    /**
     * 系统管理模块（schema.sql 表：t_tenant, t_sys_user, t_sys_role, t_sys_permission, t_sys_user_role, t_sys_role_permission）
     */
    private static void generateSystemModule() {
        System.out.println(">>> 开始生成 system 模块...");
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author("tea-chain-generator")
                        .outputDir(SYSTEM_PATH)
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(PARENT_PKG)
                        .moduleName("system")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_PATH + "/system")))
                .strategyConfig(builder -> builder
                        .addInclude("t_tenant", "t_sys_user", "t_sys_role", "t_sys_permission",
                                "t_sys_user_role", "t_sys_role_permission")
                        .addTablePrefix("t_")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%s")
                        .superClass("com.teachain.common.entity.BaseEntity")
                        .addSuperEntityColumns("id", "tenant_id", "create_time", "update_time", "create_by", "update_by", "deleted")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("update_by", FieldFill.INSERT_UPDATE)
                        )
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println(">>> system 模块生成完毕");
    }

    /**
     * 商品/配方/原料模块（schema.sql 表：t_category, t_product, t_product_spec, t_product_spec_addon,
     * t_material, t_product_recipe, t_supplier, t_material_supplier）
     */
    private static void generateProductModule() {
        System.out.println(">>> 开始生成 product 模块...");
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author("tea-chain-generator")
                        .outputDir(PRODUCT_PATH)
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(PARENT_PKG)
                        .moduleName("product")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_PATH + "/product")))
                .strategyConfig(builder -> builder
                        .addInclude("t_category", "t_product", "t_product_spec", "t_product_spec_addon",
                                "t_material", "t_product_recipe", "t_supplier", "t_material_supplier")
                        .addTablePrefix("t_")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%s")
                        .superClass("com.teachain.common.entity.BaseEntity")
                        .addSuperEntityColumns("id", "tenant_id", "create_time", "update_time", "create_by", "update_by", "deleted")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("update_by", FieldFill.INSERT_UPDATE)
                        )
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println(">>> product 模块生成完毕");
    }

    /**
     * 门店/库存/采购模块（schema.sql 表：t_store, t_store_material, t_store_material_log,
     * t_purchase_order, t_purchase_order_item, t_purchase_delivery）
     */
    private static void generateStoreModule() {
        System.out.println(">>> 开始生成 store 模块...");
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author("tea-chain-generator")
                        .outputDir(STORE_PATH)
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(PARENT_PKG)
                        .moduleName("store")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_PATH + "/store")))
                .strategyConfig(builder -> builder
                        .addInclude("t_store", "t_store_material", "t_store_material_log",
                                "t_purchase_order", "t_purchase_order_item", "t_purchase_delivery")
                        .addTablePrefix("t_")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%s")
                        .superClass("com.teachain.common.entity.BaseEntity")
                        .addSuperEntityColumns("id", "tenant_id", "create_time", "update_time", "create_by", "update_by", "deleted")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("update_by", FieldFill.INSERT_UPDATE)
                        )
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println(">>> store 模块生成完毕");
    }

    /**
     * 交易/订单/支付模块（schema.sql 表：t_order, t_order_item, t_order_payment, t_cart）
     */
    private static void generateOrderModule() {
        System.out.println(">>> 开始生成 order 模块...");
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author("tea-chain-generator")
                        .outputDir(ORDER_PATH)
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(PARENT_PKG)
                        .moduleName("order")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_PATH + "/order")))
                .strategyConfig(builder -> builder
                        .addInclude("t_order", "t_order_item", "t_order_payment", "t_cart")
                        .addTablePrefix("t_")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%s")
                        .superClass("com.teachain.common.entity.BaseEntity")
                        .addSuperEntityColumns("id", "tenant_id", "create_time", "update_time", "create_by", "update_by", "deleted")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("update_by", FieldFill.INSERT_UPDATE)
                        )
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println(">>> order 模块生成完毕");
    }

    /**
     * 会员/营销模块（schema.sql 表：t_member, t_member_point_log, t_member_level, t_coupon_template, t_coupon）
     */
    private static void generateMemberModule() {
        System.out.println(">>> 开始生成 member 模块...");
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author("tea-chain-generator")
                        .outputDir(MEMBER_PATH)
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(PARENT_PKG)
                        .moduleName("member")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_PATH + "/member")))
                .strategyConfig(builder -> builder
                        .addInclude("t_member", "t_member_point_log", "t_member_level", "t_coupon_template", "t_coupon")
                        .addTablePrefix("t_")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%s")
                        .superClass("com.teachain.common.entity.BaseEntity")
                        .addSuperEntityColumns("id", "tenant_id", "create_time", "update_time", "create_by", "update_by", "deleted")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("update_by", FieldFill.INSERT_UPDATE)
                        )
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println(">>> member 模块生成完毕");
    }

    /**
     * 报表/配置模块（schema.sql 表：t_daily_report, t_system_config）
     */
    private static void generateReportModule() {
        System.out.println(">>> 开始生成 report 模块...");
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author("tea-chain-generator")
                        .outputDir(REPORT_PATH)
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(PARENT_PKG)
                        .moduleName("report")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_PATH + "/report")))
                .strategyConfig(builder -> builder
                        .addInclude("t_daily_report", "t_system_config")
                        .addTablePrefix("t_")
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .idType(IdType.ASSIGN_ID)
                        .formatFileName("%s")
                        .superClass("com.teachain.common.entity.BaseEntity")
                        .addSuperEntityColumns("id", "tenant_id", "create_time", "update_time", "create_by", "update_by", "deleted")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("update_by", FieldFill.INSERT_UPDATE)
                        )
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        System.out.println(">>> report 模块生成完毕");
    }
}
