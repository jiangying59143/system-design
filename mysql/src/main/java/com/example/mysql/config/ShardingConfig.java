package com.example.mysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shardingsphere.api.sharding.ShardingAlgorithm;

@Configuration
public class ShardingConfig {

    @Bean(name = "mod-hash")
    public ShardingAlgorithm modShardingAlgorithm() {
        return new MyModShardingAlgorithm();
    }
}

