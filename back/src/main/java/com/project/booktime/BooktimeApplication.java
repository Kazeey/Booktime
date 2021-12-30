package com.project.booktime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
