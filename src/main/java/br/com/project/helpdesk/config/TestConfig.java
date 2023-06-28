package br.com.project.helpdesk.config;

import br.com.project.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean//executa o meotodo sempre que a aplicacao eh iniciada
    public void instanciaDB(){
        this.dbService.instanciaDB();
    }
}
