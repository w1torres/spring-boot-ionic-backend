package com.wildessilva.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wildessilva.cursomc.services.DBService;
import com.wildessilva.cursomc.services.EmailService;
import com.wildessilva.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	
	
	@Bean
	public  boolean instatiateDataBase() throws ParseException {
		dbService.instatiateDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
	    return new MockEmailService();
	}
}
