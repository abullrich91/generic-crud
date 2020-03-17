package com.abullrich.crud;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MockConfig {

    @Bean
    @Primary
    public JdbcTemplate sucursalJdbc() {
        return Mockito.mock(JdbcTemplate.class);
    }

}
