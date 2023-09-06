package com.example.mysql.config;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Collections;

public class MyModShardingAlgorithm implements HintShardingAlgorithm<Long> {
    private static final int NUM_SHARDS = 2; // 分片数量

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        // 获取分片键的值
        Long shardingKey = shardingValue.getValues().iterator().next();

        // 计算分片索引
        int shardIndex = (int) (shardingKey % NUM_SHARDS);

        // 构造分片名称
        String shardName = "ds-master." + shardIndex + ".t_user";

        // 返回分片名称
        return Collections.singletonList(shardName);
    }
}

