package com.teachain.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.member.entity.MemberLevel;
import com.teachain.member.mapper.MemberLevelMapper;
import com.teachain.member.service.MemberLevelService;
import org.springframework.stereotype.Service;

@Service
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {
}
