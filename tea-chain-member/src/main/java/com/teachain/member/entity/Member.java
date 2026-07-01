package com.teachain.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_member")
public class Member extends BaseEntity {

    private String openid;
    private String unionid;
    private String nickname;
    private String avatar;
    private String phone;
    private Integer gender;
    private LocalDate birthday;
    private Long levelId;
    private Integer totalPoints;
    private Integer availablePoints;
    private BigDecimal totalSpent;
    private Integer orderCount;
    private LocalDateTime lastOrderTime;
    private Long registerStoreId;
    private Integer status;
}
