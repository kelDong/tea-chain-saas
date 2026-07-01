package com.teachain.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("t_order_payment")
public class OrderPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long tenantId;
    private Long orderId;
    private String orderNo;
    private String paymentNo;
    private Integer payType;
    private BigDecimal payAmount;
    private Integer payStatus;
    private String transactionId;
    private LocalDateTime payTime;
    private String refundNo;
    private BigDecimal refundAmount;
    private LocalDateTime refundTime;
    private String refundReason;
    private String callbackData;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
