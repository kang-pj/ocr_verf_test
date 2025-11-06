package com.refine.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rfdb")
public class RfDbProperties {
    // RF Database properties
}