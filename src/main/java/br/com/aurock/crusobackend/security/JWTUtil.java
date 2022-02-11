package br.com.aurock.crusobackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String segredo;

    @Value("${jwt.expiration}")
    private Long tempoDeVida;

    public String geraToken(String userName){
        return Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + tempoDeVida))
                .signWith(SignatureAlgorithm.HS512,segredo.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
}
