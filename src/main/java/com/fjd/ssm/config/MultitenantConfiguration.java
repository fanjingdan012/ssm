package com.fjd.ssm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class MultitenantConfiguration {

    @Autowired
    private DataSourceProperties properties;
    private static Map<String, DataSource> dataSourceMap = new HashMap<>();

    /**
     * Defines the data source for the application
     * @return
     */
    public DataSource getTenantDataSource(String tenantId){
        if (dataSourceMap.containsKey(tenantId)) {
            System.out.println("GetDataSource:" + tenantId);
            return dataSourceMap.get(tenantId);
        } else {
            System.out.println("GetDataSource:" + "Default");
            return dataSourceMap.get("Default");
        }
    }
    @Bean
    public MultitenantDataSource multitenantDataSource() {
        Map<Object,Object> resolvedDataSources = new HashMap<>();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/test")
                .username("root")
                .password("123456");
        resolvedDataSources.put("tenant1",dataSourceBuilder.build());
        resolvedDataSources.put("Default",dataSourceBuilder.build());
        DataSourceBuilder dataSourceBuilder2 = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/test2")
                .username("root")
                .password("123456");
        resolvedDataSources.put("tenant2",dataSourceBuilder2.build());
        MultitenantDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get("Default"));
        dataSource.setTargetDataSources(resolvedDataSources);

        dataSource.afterPropertiesSet();

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(this.multitenantDataSource());
        return transactionManager;
    }
}