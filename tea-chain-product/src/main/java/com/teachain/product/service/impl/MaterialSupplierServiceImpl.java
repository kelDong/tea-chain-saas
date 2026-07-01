package com.teachain.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.product.entity.MaterialSupplier;
import com.teachain.product.mapper.MaterialSupplierMapper;
import com.teachain.product.service.MaterialSupplierService;
import org.springframework.stereotype.Service;

@Service
public class MaterialSupplierServiceImpl extends ServiceImpl<MaterialSupplierMapper, MaterialSupplier> implements MaterialSupplierService {
}
