package com.teachain.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.product.entity.Recipe;
import com.teachain.product.mapper.RecipeMapper;
import com.teachain.product.service.RecipeService;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements RecipeService {
}
