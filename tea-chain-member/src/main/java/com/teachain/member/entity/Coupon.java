package com.teachain.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_coupon")
public class Coupon extends BaseEntity {

    private Long templateId;
    private Long memberId;
    private String couponCode;
    private Integer couponType;
    private BigDecimal faceValue;
    private BigDecimal discountRate;
    private BigDecimal minAmount;
    private Integer status;
    private LocalDateTime receiveTime;
    private LocalDateTime validStart;
    private LocalDateTime validEnd;
    private LocalDateTime useTime;
    private Long useOrderId;
    private Long useStoreId;
    private Integer source;
    private String remark;
}
