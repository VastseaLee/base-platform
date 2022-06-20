package com.base.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeProperties {

    private Long dataCenterId;
    private Long machineId;
}
