package cz.zcu.fav.pia.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication // This is a Spring Boot annotation making this a Spring Boot Application entry point
@ServletComponentScan // Turns on scanning for regular Servlet API components
public class IocApp {

	// As you can see, we do not run any code here, just start Spring and let it handle the rest
	public static void main(String[] args) {
		SpringApplication.run(IocApp.class, args);
	}

}
