package com.RuanPablo2.BeanCounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BeanCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanCounterApplication.class, args);
	}

}
