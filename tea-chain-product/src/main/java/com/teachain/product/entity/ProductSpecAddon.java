package com.teachain.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_spec_addon")
public class ProductSpecAddon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long productId;

    private String addonName;

    private BigDecimal addonPrice;

    private Long materialId;

    private BigDecimal usageAmount;

    private Integer sortOrder;

    private Integer isDefault;

    private Integer status;

    private Integer maxCount;
}
