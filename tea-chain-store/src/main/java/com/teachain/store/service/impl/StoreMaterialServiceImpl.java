package com.teachain.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.store.entity.StoreMaterial;
import com.teachain.store.mapper.StoreMaterialMapper;
import com.teachain.store.service.StoreMaterialService;
import org.springframework.stereotype.Service;

@Service
public class StoreMaterialServiceImpl extends ServiceImpl<StoreMaterialMapper, StoreMaterial> implements StoreMaterialService {
}
