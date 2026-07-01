package com.teachain.store.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("t_store_material_log")
public class StoreMaterialLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long tenantId;

    private Long storeId;

    private Long materialId;

    private Integer logType;

    private BigDecimal quantity;

    private BigDecimal beforeStock;

    private BigDecimal afterStock;

    private String batchNo;

    private BigDecimal unitCost;

    private BigDecimal totalCost;

    private LocalDate expiryDate;

    private Long relatedOrderId;

    private String relatedOrderType;

    private Long operatorId;

    private String operatorName;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
