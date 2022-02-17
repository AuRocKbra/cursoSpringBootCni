package br.com.aurock.crusobackend.config;

import br.com.aurock.crusobackend.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String criaBanco;

    @Bean
    public boolean instanciaDateBase() throws ParseException {
        if(criaBanco.equals("create")){
            dbService.instanciaDateBase();
            return true;
        }
        return false;
    }
}
