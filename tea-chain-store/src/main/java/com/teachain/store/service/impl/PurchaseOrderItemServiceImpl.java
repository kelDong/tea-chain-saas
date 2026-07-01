package com.teachain.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.store.entity.PurchaseOrderItem;
import com.teachain.store.mapper.PurchaseOrderItemMapper;
import com.teachain.store.service.PurchaseOrderItemService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderItemServiceImpl extends ServiceImpl<PurchaseOrderItemMapper, PurchaseOrderItem> implements PurchaseOrderItemService {
}
