package com.agnext.zohodatasync.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "connection.config")
@Configuration
@Setter
@Getter
public class RestConnectionConfigurations {
	private long connectionTimeoutInSeconds;
	private long readTimeoutInSeconds;
	private String numberOfRetries;
	private String backOffDelay;
}
