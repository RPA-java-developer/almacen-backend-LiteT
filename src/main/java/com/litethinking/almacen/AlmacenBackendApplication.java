package com.litethinking.almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication
//@EnableSwagger2
public class AlmacenBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlmacenBackendApplication.class, args);
	}

}
