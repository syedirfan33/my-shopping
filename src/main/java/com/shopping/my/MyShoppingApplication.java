package com.shopping.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.shopping.my.repo")
@EntityScan("com.shopping.my.entity")
public class MyShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShoppingApplication.class, args);
	}

}
