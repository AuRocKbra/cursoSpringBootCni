package br.com.aurock.crusobackend.security;

import br.com.aurock.crusobackend.domain.dto.CredenciaisDTO;
import br.com.aurock.crusobackend.security.exceptions.FalhaNoProcessoDeAutenticacao;
import br.com.aurock.crusobackend.util.Mensagens;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        try{
            CredenciaisDTO credenciaisDTO = new ObjectMapper().readValue(request.getInputStream(),CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(),credenciaisDTO.getSenha(), new ArrayList<>());
            return  authenticationManager.authenticate(authenticationToken);

        }
        catch (IOException e) {
            throw new FalhaNoProcessoDeAutenticacao(Mensagens.MSG_ERRO_AUTENTICACAO);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication auth) throws IOException, ServletException{
        String username = ((UsuarioSS) auth.getPrincipal()).getUsername();
        String token = jwtUtil.geraToken(username);
        response.addHeader("Authorization","Bearer "+token);
        response.addHeader("access-control-expose-headers","Authorization");
    }
}
