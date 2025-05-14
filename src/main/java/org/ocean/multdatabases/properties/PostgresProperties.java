package org.ocean.multdatabases.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource1")
@Component
@Data
public class PostgresProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
