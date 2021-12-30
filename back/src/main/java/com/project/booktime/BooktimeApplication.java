package com.project.booktime;

import com.project.booktime.repository.IUserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@SpringBootApplication
public class BooktimeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(BooktimeApplication.class, args);
	}

	/*
	A décommenter pour voir la liste complète des beans fournies par Sprint Boot

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context)
	{
		return args -> {
			System.out.println("Beans fournies par Spring Boot : ");
			String[] beanNames = context.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames)
			{
				System.out.println(beanName);
			}
		};
	}
	 */
}
