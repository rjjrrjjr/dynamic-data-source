package com.rj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ruanjin
 * @since 2019/5/22 11:36
 */
@Component
@ConfigurationProperties(prefix = "db2.jdbc")
public class DataSourceOneBACK {
    
    private String driverName;

    private String url;

    private String username;

    private String password;
}
