package cz.zcu.fav.pia.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class IocApp {

	public static void main(String[] args) {
		SpringApplication.run(IocApp.class, args);
	}

}
