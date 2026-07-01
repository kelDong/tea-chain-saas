package com.teachain.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.teachain.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_sys_permission")
public class SysPermission extends BaseEntity {

    private Long parentId;

    private String name;

    private String permissionKey;

    private Integer type;

    private String path;

    private String component;

    private String icon;

    private Integer sortOrder;

    private Integer visible;

    private Integer status;

    private String clientType;

    private String remark;
}
