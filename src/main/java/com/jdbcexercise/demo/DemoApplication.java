package com.jdbcexercise.demo;

import com.jdbcexercise.demo.config.RsaKeyConfigProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import lombok.extern.java.Log;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@Log


public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception{
		log.info("Montrack Backend Application started successfully");
	}

}
