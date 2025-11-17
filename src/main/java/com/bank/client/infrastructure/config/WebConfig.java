package com.bank.client.infrastructure.config;

import org.springframework.context.annotation.Bean;

public class WebConfig {
    @Bean
    public TraceIdWebFilter traceIdWebFilter() {
        return new TraceIdWebFilter();
    }
}
