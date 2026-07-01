package com.teachain.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private String name;

    private String code;

    private String image;

    private String images;

    private String description;

    private BigDecimal basePrice;

    private BigDecimal costPrice;

    private Integer productType;

    private Integer isHot;

    private Integer isNew;

    private Integer status;

    private Integer sortOrder;

    private String remark;
}
