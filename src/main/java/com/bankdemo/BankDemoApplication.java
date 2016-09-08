package com.bankdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.bankdemo"})
public class BankDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BankDemoApplication.class, args);
	}
}
