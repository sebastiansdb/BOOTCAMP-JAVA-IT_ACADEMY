package com.S1T2N123;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// TO DO

// Implementar patron fabrica para:
// 	Player
// 	Game

// Mirar BEARER (JWT authentication)


//									NUEVO
//	----------------------------------------------------------------
// Tareas Mañana JUEVES
//
// Refactorizar:
//	- Seguridad & Packages
//
// TESTS
//
// findByEmail
//


//	----------------------------------------------------------------
// Entender codigo seguridad y seguirlo en el diagrama en bloques

// campo userEmail en player

// User con muchos players

// Ver si lo implemento con SWAGGER, si no, sacarlo de "SecurityConfiguration"

/*


	- PlayerServiceImpl
	- GameServiceimpl (Borrar ultima parte)

 */

@SpringBootApplication
public class S1T2N123Application {

	public static void main(String[] args) {
		SpringApplication.run(S1T2N123Application.class, args);
	}

}
