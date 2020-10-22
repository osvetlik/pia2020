package cz.zcu.fav.pia.jsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration
public class JsfApp2 {

	public static void main(String[] args) {
		SpringApplication.run(JsfApp2.class, args);
	}

}
