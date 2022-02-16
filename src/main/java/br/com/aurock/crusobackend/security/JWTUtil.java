package br.com.aurock.crusobackend.security;

import io.jsonwebtoken.Claims;
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

    public boolean isTokenValido(String token){
        Claims claims = getClaims(token);
        if(claims != null){
            String userName = claims.getSubject();
            Date dataExpiracao = claims.getExpiration();
            Date instante = new Date(System.currentTimeMillis());
            if(userName != null && dataExpiracao != null && instante.before(dataExpiracao)){
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(segredo.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);
        if (claims != null){
            return claims.getSubject();
        }
        return null;
    }
}
