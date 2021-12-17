package com.project.booktime;

import com.project.booktime.mongodb.repository.testRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories
public class BooktimeApplication implements CommandLineRunner
{
	@Autowired
	testRepository testRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(BooktimeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
