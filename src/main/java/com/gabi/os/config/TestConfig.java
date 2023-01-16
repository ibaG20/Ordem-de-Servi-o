package com.gabi.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gabi.os.services.DBService;

@Configuration
@Profile("test") //NOME DO PERFIL (= TEST)
public class TestConfig {
	
	@Autowired
	private DBService dbService;

	@Bean //SEMPRE QUE A CLASSE FOR INSTANCIADA PUXA ESSE METODO
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}

}
