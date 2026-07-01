package com.teachain.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order")
public class Order extends BaseEntity {

    private Long storeId;
    private String orderNo;
    private Integer orderType;
    private Integer orderSource;
    private Long memberId;
    private String tableNo;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal actualAmount;
    private BigDecimal pointDeduct;
    private Integer pointEarned;
    private Long couponId;
    private Integer payType;
    private Integer payStatus;
    private Integer orderStatus;
    private String cancelReason;
    private LocalDateTime orderTime;
    private LocalDateTime payTime;
    private LocalDateTime finishTime;
    private String pickupCode;
    private String customerNote;
    private Long operatorId;
    private String remark;
}
