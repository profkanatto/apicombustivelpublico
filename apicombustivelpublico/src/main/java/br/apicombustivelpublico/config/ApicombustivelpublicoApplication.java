package br.apicombustivelpublico.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@SpringBootApplication
@ComponentScan({
	"br.apicombustivelpublico.config", 
	"br.apicombustivelpublico.config.login",
	"br.apicombustivelpublico.form",
	"br.apicombustivelpublico.controller",
	"br.apicombustivelpublico.controller.request",
	"br.apicombustivelpublico.controller.response",
	})
@EntityScan(basePackages = {"br.apicombustivelpublico.model"} )
@EnableMongoRepositories(basePackages = {"br.apicombustivelpublico.repository"})
@EnableSpringDataWebSupport
public class ApicombustivelpublicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicombustivelpublicoApplication.class, args);
	}		

}
