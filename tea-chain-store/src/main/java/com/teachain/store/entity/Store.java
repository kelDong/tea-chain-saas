package com.teachain.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_store")
public class Store extends BaseEntity {

    private String storeName;

    private String storeCode;

    private Integer storeType;

    private String province;

    private String city;

    private String district;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String contactName;

    private String contactPhone;

    private String businessHours;

    private String logo;

    private String images;

    private LocalDate openDate;

    private Integer status;

    private String remark;
}
