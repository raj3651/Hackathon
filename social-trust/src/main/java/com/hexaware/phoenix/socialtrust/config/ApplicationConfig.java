package com.hexaware.phoenix.socialtrust.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAutoConfiguration
@EnableAsync
@ComponentScan(basePackages = {"com.hexaware.phoenix.socialtrust.resource", "com.hexaware.phoenix.socialtrust.dao", "com.hexaware.phoenix.socialtrust.service"})
public class ApplicationConfig {
}
