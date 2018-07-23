package com.fjd.ssm.controller;

import com.fjd.ssm.config.MultitenantDataSource;
import com.fjd.ssm.tenant.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import com.fjd.ssm.core.Member;
import com.fjd.ssm.service.MemberService;

@RestController
public class LoginController {
	@Autowired
	MemberService memberService;
	@Autowired
	ApplicationContext ctx;
	@RequestMapping(value = "/hello")
	public String hello(){
		System.out.println("Hello");
		return "hi";
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

	@GetMapping("/api/tenant/register")
	@ResponseBody
	public String tenantRegister(@RequestParam String username, @RequestParam String password, @RequestParam String url, @RequestParam String tenantName) {

		// 在配置数据库中新增数据源信息.我是通过脚本insert的，这里省略
		// insertDataSource()
		// sysProperties 是系统统一取properties 的类
		// 获取context.

		MultitenantDataSource multitenantDataSource = ctx.getBean(MultitenantDataSource.class);
		// 构建新数据源

		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
				//.driverClassName("com.sap.db.jdbc.Driver")
				//com.mysql.jdbc.Driver
				.url(url)
				.username(username)
				.password(password);
		//resolvedDataSources.put(tenantName,dataSourceBuilder.build());

		// 重设并通知，维护的TargetDataSource
		multitenantDataSource.addDataSourceToTargetDataSource(tenantName, dataSourceBuilder.build());
		return "success";
	}
}
