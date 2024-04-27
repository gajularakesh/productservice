package com.scalar.productservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfigurations {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
