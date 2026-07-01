package com.teachain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_sys_role")
public class SysRole extends BaseEntity {

    private String roleName;

    private String roleKey;

    private Integer sortOrder;

    private Integer status;

    private Integer dataScope;

    private String remark;
}
