package com.fjd.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude= {DataSourceAutoConfiguration.class})
@MapperScan({"com.fjd.ssm.mapper"})
public class SSMApplication{


    public static void main(String[] args) {

        SpringApplication.run(SSMApplication.class, args);
    }


}