package com.example.plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySources({@PropertySource("log4j.properties"), @PropertySource("redis.properties"), @PropertySource("rabbitmq.properties")})
public class PlanApplication {

	public static void main (String[] args) {
		SpringApplication.run(PlanApplication.class, args);
	}
}
