package com.S04T02N03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
                            			TO DO
    Checker getAll desde navegador. A veces anda y otras no.
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class S04T02N03Application {

	public static void main(String[] args) {
		SpringApplication.run(S04T02N03Application.class, args);
	}

}
