package br.edu.ifsp.prw3.prw3_avaliacao3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

// Filipe Bueno Balsani
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class Prw3Avaliacao3Application {

	public static void main(String[] args) {
		SpringApplication.run(Prw3Avaliacao3Application.class, args);
	}

}
