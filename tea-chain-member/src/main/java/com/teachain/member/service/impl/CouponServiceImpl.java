package com.teachain.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.member.entity.Coupon;
import com.teachain.member.mapper.CouponMapper;
import com.teachain.member.service.CouponService;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
}
