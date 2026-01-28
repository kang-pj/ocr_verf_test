package com.refine.properties;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rfdb")
@Getter
@Setter
public class RfDbProperties {
    // RF Database properties
    private HikariConfig hikariConfig = new HikariConfig();
    private String configLocation = "classpath:META-INF/mybatis/mybatis-config.xml";
    private String mapperLocations = "classpath:com/refine/**/dao/*Mapper.xml";
}