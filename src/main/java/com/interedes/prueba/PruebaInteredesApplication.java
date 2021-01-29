package com.interedes.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PruebaInteredesApplication implements CommandLineRunner {	

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(PruebaInteredesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		String pass = "12345";
		String encodedPass = bCryptEncoder.encode(pass);
		
		System.out.println("Password: " + encodedPass);
		
	}

}
