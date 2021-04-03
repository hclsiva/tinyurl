package study.tinyurl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.tinyurl.database.CassandraDatabaseAccess;

@Configuration
public class ApplicationConfig {

	@Bean(initMethod="init")
	public CassandraDatabaseAccess getCassandraDatabaseAccess() {
		return new CassandraDatabaseAccess();
	}
}
