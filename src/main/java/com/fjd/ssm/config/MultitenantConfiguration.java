package com.fjd.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(MultitenantDataSourceRegister.class)
public class MultitenantConfiguration {

    @Autowired
    private MultitenantDataSource multitenantDataSource;

}