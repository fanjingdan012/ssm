package com.fjd.ssm.service;

import org.springframework.stereotype.Service;

import com.fjd.ssm.core.Member;

@Service
public interface MemberService {
	Member login(String name, String passsword) throws Exception;
}
