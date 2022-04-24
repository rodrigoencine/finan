package com.example.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.financeiro")
public class SistemaFinanceiroApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaFinanceiroApplication.class, args);
		
	}
	
}
