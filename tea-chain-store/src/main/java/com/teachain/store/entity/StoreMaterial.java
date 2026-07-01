package com.teachain.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_store_material")
public class StoreMaterial extends BaseEntity {

    private Long storeId;

    private Long materialId;

    private BigDecimal currentStock;

    private BigDecimal minStock;

    private BigDecimal maxStock;

    private String batchNo;

    private LocalDate productionDate;

    private LocalDate expiryDate;

    private BigDecimal unitCost;

    private Integer status;
}
