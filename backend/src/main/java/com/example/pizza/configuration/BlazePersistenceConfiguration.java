package com.example.pizza.configuration;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


@Configuration
public class BlazePersistenceConfiguration {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Creates and configures the CriteriaBuilderFactory bean.
     *
     * @return The CriteriaBuilderFactory bean used for building criteria queries.
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy(false)
    public CriteriaBuilderFactory createCriteriaBuilderFactory() {

        // Create a new CriteriaBuilderConfiguration using Blaze-Persistence's default configuration.
        CriteriaBuilderConfiguration config = Criteria.getDefault();

        // Use the EntityManagerFactory to create the CriteriaBuilderFactory.
        return config.createCriteriaBuilderFactory(entityManagerFactory);
    }
}

