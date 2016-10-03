package com.bankdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bankdemo"})
public class BankDemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(new Class[] {BankDemoApplication.class}, args);
    }
}
