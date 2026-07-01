package com.teachain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_tenant")
public class Tenant extends BaseEntity {

    private String tenantName;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private String province;

    private String city;

    private String district;

    private String address;

    private String businessLicense;

    private String contractNo;

    private LocalDate contractStart;

    private LocalDate contractEnd;

    private BigDecimal franchiseFee;

    private Integer status;

    private String remark;
}
