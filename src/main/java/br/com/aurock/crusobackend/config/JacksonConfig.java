package br.com.aurock.crusobackend.config;

import br.com.aurock.crusobackend.domain.PagamentoBoleto;
import br.com.aurock.crusobackend.domain.PagamentoCartao;
import br.com.aurock.crusobackend.service.MockMailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){
        return new Jackson2ObjectMapperBuilder(){
          public void configure(ObjectMapper objectMapper){
              objectMapper.registerSubtypes(PagamentoCartao.class);
              objectMapper.registerSubtypes(PagamentoBoleto.class);
              super.configure(objectMapper);
          };
        };
    }

    @Bean
    public MockMailService emailService(){
        return new MockMailService();
    }
}
