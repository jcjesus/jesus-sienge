package br.com.jesus.challenge.sienge;

import br.com.jesus.challenge.sienge.services.TransportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestConfiguration
@Configuration
@EnableWebMvc
@ComponentScan( basePackages = { "br.com.jesus.challenge.sienge" } )
@EnableJpaRepositories(basePackages = "br.com.jesus.challenge.sienge.repositories")
public class JesusSiengeApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Bean
	public TransportService transportService() {
		return Mockito.mock(TransportService.class);
	}

}

