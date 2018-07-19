package com.fjd.ssm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjd.common.StringUtil;
import com.fjd.ssm.core.Member;
import com.fjd.ssm.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper memberMapper;
	
	public Member login(String name, String passsword) throws Exception {
		System.out.println(name + passsword);
		if(StringUtil.isNullOrZero(name)){
			System.out.println("name is null");
			return null;
		}
		if(StringUtil.isNullOrZero(passsword)){
			System.out.println("password is null");
			return null;
		}
		Member member = memberMapper.selectMemberByName(name);
		return member;
	}

}
