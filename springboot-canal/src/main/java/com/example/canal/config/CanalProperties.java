package com.example.canal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "canal.server") // 使用配置文件中以"canal"为前缀的属性
public class CanalProperties {
    private String hostname;
    private int port;
    private String destination;
    private String username;
    private String password;
}

