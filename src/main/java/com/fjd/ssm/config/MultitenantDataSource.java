package com.fjd.ssm.config;

import com.fjd.ssm.tenant.TenantContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultitenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
    private ConcurrentHashMap<String, DataSource> backupTargetDataSources = new ConcurrentHashMap<>();

    public void addDataSourceToTargetDataSource(String key ,DataSource ds){
        this.backupTargetDataSources.put(key, ds);
        this.setTargetDataSource(this.backupTargetDataSources);
    }


    public void setTargetDataSource(Map targetDataSource){
        super.setTargetDataSources(targetDataSource);
        this.afterPropertiesSet();
    }
}
