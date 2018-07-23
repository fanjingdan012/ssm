package com.fjd.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import java.util.HashMap;
import java.util.Map;


@Configuration
@Import(MultitenantDataSourceRegister.class)
public class MultitenantConfiguration {

    @Autowired
    private MultitenantDataSource multitenantDataSource;

}