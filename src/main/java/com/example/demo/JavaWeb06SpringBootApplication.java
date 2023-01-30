package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
@MapperScan("com.example.mapper")
public class JavaWeb06SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWeb06SpringBootApplication.class, args);
	}

}
