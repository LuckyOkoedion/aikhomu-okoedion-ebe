package com.AikhomuLuckyOkoedion.OnlineBookStore.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    public AbstractRoutingDataSource dataSource() {
        AbstractRoutingDataSource dataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                // Simulate switching between primary and replica
                return Math.random() > 0.5 ? "primary" : "replica";
            }
        };

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("primary", primaryDataSource());
        targetDataSources.put("replica", replicaDataSource());
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }

    // Mock primary and replica data sources
    private Object primaryDataSource() {
        return "PrimaryDB";
    }

    private Object replicaDataSource() {
        return "ReplicaDB";
    }
}

