package com.fjd.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fjd.ssm.core.Member;
import com.fjd.ssm.service.MemberService;

@RestController
public class LoginController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println("Hello");
		return "hi";
	}

	
	@RequestMapping(value = "/members/{name}")
	public Member member(@PathVariable("name") String name){
		try {
			Member member = memberService.login(name, "145");
			return member;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
