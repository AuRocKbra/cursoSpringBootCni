package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.security.UsuarioSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UsuarioSS obterUsuarioLogado(){
        try {
            return (UsuarioSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
