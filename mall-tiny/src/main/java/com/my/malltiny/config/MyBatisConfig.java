package com.my.malltiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan({"com.my.malltiny.mbg.mapper","com.my.malltiny.dao"})
public class MyBatisConfig {
}
