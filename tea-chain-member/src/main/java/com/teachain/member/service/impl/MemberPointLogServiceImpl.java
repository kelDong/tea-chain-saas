package com.teachain.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teachain.member.entity.MemberPointLog;
import com.teachain.member.mapper.MemberPointLogMapper;
import com.teachain.member.service.MemberPointLogService;
import org.springframework.stereotype.Service;

@Service
public class MemberPointLogServiceImpl extends ServiceImpl<MemberPointLogMapper, MemberPointLog> implements MemberPointLogService {
}
