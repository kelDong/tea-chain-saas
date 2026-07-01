package com.teachain.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("t_material_supplier")
public class MaterialSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long tenantId;

    private Long materialId;

    private Long supplierId;

    private BigDecimal supplyPrice;

    private Integer isPrimary;

    private Integer leadTimeDays;

    private BigDecimal minOrderQty;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
