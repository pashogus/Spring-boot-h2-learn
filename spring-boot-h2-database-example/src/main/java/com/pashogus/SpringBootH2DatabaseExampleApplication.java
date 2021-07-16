package com.pashogus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//https://dzone.com/articles/json-web-token-how-to-secure-spring-boot-rest-api

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootH2DatabaseExampleApplication 
{
public static void main(String[] args) 
{
SpringApplication.run(SpringBootH2DatabaseExampleApplication.class, args);
}
}
