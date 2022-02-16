package br.com.aurock.crusobackend.config;

import br.com.aurock.crusobackend.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciaDateBase() throws ParseException {
        dbService.instanciaDateBase();
        return true;
    }
}
