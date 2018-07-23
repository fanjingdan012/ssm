package com.fjd.ssm.config;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class MultitenantDataSourceRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotaion, BeanDefinitionRegistry registry) {
        System.out.println("registerBeanDefinitions");
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MultitenantDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();

        mpv.addPropertyValue("defaultTargetDataSource", getDefaultDataSources().get("Default"));
        mpv.addPropertyValue("targetDataSources",getDefaultDataSources());
        registry.registerBeanDefinition("dataSource", beanDefinition);
    }

    private Map<String,DataSource> getDefaultDataSources(){
                Map<String,DataSource> resolvedDataSources = new HashMap<>();

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost/test")
                .username("root")
                .password("123456");
        resolvedDataSources.put("tenant1",dataSourceBuilder.build());
        resolvedDataSources.put("Default",dataSourceBuilder.build());
//        DataSourceBuilder dataSourceBuilder2 = DataSourceBuilder.create()
//                .url("jdbc:mysql://localhost/test2")
//                .username("root")
//                .password("123456");
//        resolvedDataSources.put("tenant2",dataSourceBuilder2.build());


 //       MultitenantDataSource dataSource = new MultitenantDataSource();
   //     dataSource.setDefaultTargetDataSource(resolvedDataSources.get("Default"));
      //  dataSource.setTargetDataSources(resolvedDataSources);

        //dataSource.afterPropertiesSet();
        return resolvedDataSources;
    }


}
