package com.loznica.blink;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlinkApplication implements CommandLineRunner {

	@Override
	public void run(String... args) {

	}
	public static void main(String[] args) {
		SpringApplication.run(BlinkApplication.class, args);}
}
