package com.abullrich.crud.config;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@Import(SucursalJdbcConfig.class)
@EnableJpaRepositories(
    basePackages = "com.abullrich.crud.repository",
    entityManagerFactoryRef = "marketingEventHofDbEntityManagerFactory",
    transactionManagerRef = "marketingEventHofDbTransactionManager"
)
public class SucursalJpaConfig {

    private final DataSource sucursalDataSource;

    @Bean
    @ConditionalOnClass(DataSource.class)
    public LocalContainerEntityManagerFactoryBean marketingEventHofDbEntityManagerFactory() {
        return EntityManagerFactorySupport.entityManagerFactory(
            sucursalDataSource,
            "com.abullrich.crud.repository.entity",
            "abullrichDbPersistenceUnit"
        );
    }

    @Bean
    public PlatformTransactionManager marketingEventHofDbTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(EntityManagerFactorySupport.entityManagerFactory(
            sucursalDataSource,
            "com.playtika.hof.marketing.repository.entity",
            "abullrichDbPersistenceUnit"
        ).getObject());
        return manager;
    }
}