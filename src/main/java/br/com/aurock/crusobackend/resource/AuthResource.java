package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.security.JWTUtil;
import br.com.aurock.crusobackend.security.UsuarioSS;
import br.com.aurock.crusobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping(value = "/refresh_token")
    public ResponseEntity<Void> revalidarToken(HttpServletResponse response){
        UsuarioSS usuarioSS = UserService.obterUsuarioLogado();
        String token = jwtUtil.geraToken(usuarioSS.getUsername());
        response.addHeader("Authorization","Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
