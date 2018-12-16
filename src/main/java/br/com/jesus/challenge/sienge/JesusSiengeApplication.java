package br.com.jesus.challenge.sienge;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;


@SpringBootApplication
@Configuration
public class JesusSiengeApplication {

    private static Logger logger = LoggerFactory.getLogger(JesusSiengeApplication.class);

    public static void main(String[] args) {
        logger.debug("Default Locale is: " + Locale.getDefault());
        SpringApplication.run(JesusSiengeApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}

