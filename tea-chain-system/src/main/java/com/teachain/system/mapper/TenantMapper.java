package com.teachain.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teachain.system.entity.Tenant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {
}
