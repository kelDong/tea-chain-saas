package com.teachain.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.store.entity.StoreMaterialLog;
import com.teachain.store.mapper.StoreMaterialLogMapper;
import com.teachain.store.service.StoreMaterialLogService;
import org.springframework.stereotype.Service;

@Service
public class StoreMaterialLogServiceImpl extends ServiceImpl<StoreMaterialLogMapper, StoreMaterialLog> implements StoreMaterialLogService {
}
