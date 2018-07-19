package com.fjd.ssm.controller;

import com.fjd.ssm.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@RequestMapping(value = "/login")
	public String login(String name, String password){
		
		try {
			Member member = memberService.login(name, password);
			if(member == null){
				System.out.println("no such member");
				return "hi";
			}else {
				System.out.println("member found");
				return "mainPage";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("exception when login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/members/{name}")
	public Member member(@PathVariable("name") String name, @RequestHeader("X-TenantID") String tenantName){
		TenantContext.setCurrentTenant(tenantName);
		try {
			Member member = memberService.login(name, "145");
			return member;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("exception when login");
			return null;
		}

	}
}
