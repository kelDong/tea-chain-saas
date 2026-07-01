package com.teachain.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_supplier")
public class Supplier extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private String province;

    private String city;

    private String address;

    private String bankName;

    private String bankAccount;

    private String taxNo;

    private LocalDate cooperationStart;

    private LocalDate cooperationEnd;

    private Integer status;

    private String remark;
}
