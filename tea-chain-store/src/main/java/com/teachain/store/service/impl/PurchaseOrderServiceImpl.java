package com.teachain.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.store.entity.PurchaseOrder;
import com.teachain.store.mapper.PurchaseOrderMapper;
import com.teachain.store.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {
}
