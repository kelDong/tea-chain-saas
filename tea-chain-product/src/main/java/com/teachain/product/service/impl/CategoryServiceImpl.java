package com.teachain.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.product.entity.Category;
import com.teachain.product.mapper.CategoryMapper;
import com.teachain.product.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
