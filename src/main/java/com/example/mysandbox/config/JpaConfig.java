package com.example.mysandbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // Class kosong, hanya perlu anotasi untuk mengaktifkan JPA Auditing
}
