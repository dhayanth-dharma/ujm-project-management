package com.itcps2.filling;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.itcps2.service.UserInteractionSingleton;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan

//@EnableJpaRepositories(basePackages = "com.itcps2.repository")
//@ComponentScan({"com.itcps2.service"})
@EntityScan("com.itcps2.filling.model")
@EnableJpaRepositories("com.itcps2.repository")


@EnableSwagger2

//SpringBootServletInitializer
public class FillingApplication {
	
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
		SpringApplication.run(FillingApplication.class, args);
		UserInteractionSingleton userInt=UserInteractionSingleton.getInstance();
		userInt.setStop(false);
		SSLUtil.turnOffSslChecking();
	}

}
