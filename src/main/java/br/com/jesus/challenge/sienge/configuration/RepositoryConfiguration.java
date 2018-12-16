package br.com.jesus.challenge.sienge.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan(basePackages = {"br.com.jesus.challenge.senge.models"})
@EnableJpaRepositories(basePackages = {"br.com.jesus.challenge.senge.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
