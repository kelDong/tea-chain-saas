package com.teachain.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.member.entity.CouponTemplate;
import com.teachain.member.mapper.CouponTemplateMapper;
import com.teachain.member.service.CouponTemplateService;
import org.springframework.stereotype.Service;

@Service
public class CouponTemplateServiceImpl extends ServiceImpl<CouponTemplateMapper, CouponTemplate> implements CouponTemplateService {
}
