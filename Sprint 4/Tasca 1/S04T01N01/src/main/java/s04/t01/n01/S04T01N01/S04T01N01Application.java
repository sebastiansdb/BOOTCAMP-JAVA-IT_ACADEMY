package s04.t01.n01.S04T01N01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import s04.t01.n01.S04T01N01.controllers.HelloWorldController;

@SpringBootApplication
@RestController
public class S04T01N01Application {

	public static void main(String[] args) {
		SpringApplication.run(S04T01N01Application.class, args);
	}


	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, World!";
	}

}
