package br.com.project.helpdesk.config;

import br.com.project.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean//executa o meotodo sempre que a aplicacao eh iniciada
    public boolean instanciaDB(){
        if(value.equals("create")){
            this.dbService.instanciaDB();
        }
        return false;
    }
}
