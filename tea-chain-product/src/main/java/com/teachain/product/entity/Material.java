package com.teachain.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_material")
public class Material extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String category;

    private String unit;

    private String spec;

    private BigDecimal purchasePrice;

    private Integer shelfLifeDays;

    private String storageCondition;

    private BigDecimal minStock;

    private Integer status;

    private String remark;
}
