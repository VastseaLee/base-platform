package com.base.common.config;

import com.base.common.properties.SnowflakeProperties;
import com.base.common.utils.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@EnableConfigurationProperties({SnowflakeProperties.class})
public class SnowflakeAutoConfigure {

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    @Bean
    @Primary
    public Snowflake snowFlake(){
        return new Snowflake(snowflakeProperties.getDataCenterId(),snowflakeProperties.getMachineId());
    }
}
