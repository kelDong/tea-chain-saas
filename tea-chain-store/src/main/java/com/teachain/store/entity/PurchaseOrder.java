package com.teachain.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_purchase_order")
public class PurchaseOrder extends BaseEntity {

    private Long storeId;

    private String orderNo;

    private BigDecimal totalAmount;

    private BigDecimal totalQuantity;

    private Integer status;

    private LocalDateTime orderTime;

    private LocalDateTime auditTime;

    private Long auditBy;

    private LocalDateTime deliveryTime;

    private LocalDateTime receiveTime;

    private Long receiveBy;

    private String deliveryAddress;

    private String remark;
}
