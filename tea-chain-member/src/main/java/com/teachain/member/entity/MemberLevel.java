package com.teachain.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_member_level")
public class MemberLevel extends BaseEntity {

    private String levelName;
    private Integer levelValue;
    private Integer minGrowth;
    private BigDecimal pointRate;
    private BigDecimal discountRate;
    private Long birthdayCoupon;
    private String icon;
    private String bgImage;
    private String description;
    private Integer status;
}
