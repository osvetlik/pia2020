package cz.zcu.fav.pia.jsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JsfApp {

	public static void main(String[] args) {
		SpringApplication.run(JsfApp.class, args);
	}

}
