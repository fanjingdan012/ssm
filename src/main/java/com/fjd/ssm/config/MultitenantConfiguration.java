package com.fjd.ssm.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class MultitenantConfiguration {

    @Bean
    public MultitenantDataSource multitenantDataSource() {
        Map<Object,Object> resolvedDataSources = new HashMap<>();
        //db1
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/test")
                .username("root")
                .password("207710");
        resolvedDataSources.put("tenant1",dataSourceBuilder.build());
        resolvedDataSources.put("Default",dataSourceBuilder.build());

        //db2
        DataSourceBuilder dataSourceBuilder2 = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/test2")
                .username("root")
                .password("207710");
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