package org.ocean.multdatabases.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.ocean.multdatabases.properties.H2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.ocean.multdatabases.repos.h2",
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager"
)
public class H2DatabaseConfig {

    @Autowired
    H2Properties h2Properties;


    @Bean("h2Database")
    public DataSource h2Database() {
        HikariConfig dataSource = new HikariConfig();
        dataSource.setDriverClassName(h2Properties.getDriverClassName());
        dataSource.setJdbcUrl(h2Properties.getUrl());
        dataSource.setUsername(h2Properties.getUsername());
        dataSource.setPassword(h2Properties.getPassword());

        dataSource.setMaximumPoolSize(10);
        return new HikariDataSource(dataSource);
    }

    @Bean("h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(h2Database());
        factory.setPackagesToScan("org.ocean.multdatabases.models.h2");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");

        factory.setJpaProperties(jpaProperties);

        return factory;
    }

    @Bean("h2TransactionManager")
    public PlatformTransactionManager h2TransactionManager(@Qualifier("h2EntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }


}
