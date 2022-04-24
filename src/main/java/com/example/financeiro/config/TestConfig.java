package com.example.financeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.financeiro.util.DBserver;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBserver server;
	
	@Bean
	public boolean  init() {
		this.server.initTeste();
		return true;
	}
}
