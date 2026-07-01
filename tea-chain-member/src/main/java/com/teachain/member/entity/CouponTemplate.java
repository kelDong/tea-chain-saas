package com.teachain.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_coupon_template")
public class CouponTemplate extends BaseEntity {

    private String name;
    private Integer couponType;
    private BigDecimal faceValue;
    private BigDecimal discountRate;
    private BigDecimal minAmount;
    private BigDecimal maxDiscount;
    private Integer applicableType;
    private String applicableIds;
    private Integer totalCount;
    private Integer issuedCount;
    private Integer usedCount;
    private Integer perMemberLimit;
    private Integer validType;
    private LocalDateTime validStart;
    private LocalDateTime validEnd;
    private Integer validDays;
    private Integer status;
    private String remark;
}
