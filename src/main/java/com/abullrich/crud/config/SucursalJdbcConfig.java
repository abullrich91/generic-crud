package com.abullrich.crud.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@EnableConfigurationProperties
public class SucursalJdbcConfig {

    @Bean
    @ConfigurationProperties("mysql.database")
    public HikariConfig sucursalProperties() {
        return new HikariConfig();
    }

    @Bean
    public HikariDataSource sucursalDataSource(final HikariConfig sucursalProperties) {
        return new HikariDataSource(sucursalProperties);
    }

    @Bean
    public JdbcTemplate sucursalJdbc(final HikariDataSource sucursalDataSource) {
        return new JdbcTemplate(sucursalDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate sucursalNamedJdbc(final HikariDataSource sucursalDataSource) {
        return new NamedParameterJdbcTemplate(sucursalDataSource);
    }
}
