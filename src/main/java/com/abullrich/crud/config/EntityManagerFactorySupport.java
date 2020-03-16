package com.abullrich.crud.config;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import lombok.val;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public final class EntityManagerFactorySupport {

    public static LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
                                                                              final String modelPackage,
                                                                              final String persistenceUnitName) {
        val emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan(modelPackage);
        emf.setPersistenceUnitName(persistenceUnitName);
        emf.setValidationMode(ValidationMode.NONE);
        return emf;
    }
}
