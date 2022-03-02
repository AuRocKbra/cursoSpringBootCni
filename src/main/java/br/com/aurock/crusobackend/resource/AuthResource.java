package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.DTO.EmailDTO;
import br.com.aurock.crusobackend.security.JWTUtil;
import br.com.aurock.crusobackend.security.UsuarioSS;
import br.com.aurock.crusobackend.service.AuthService;
import br.com.aurock.crusobackend.service.UserService;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    private final Log logger = new Log(this);

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> revalidarToken(HttpServletResponse response){
        logger.getLogger().info(Mensagens.MSG_REQUISICAO_CRIACAO_OBJETO,getClass().getSimpleName());
        UsuarioSS usuarioSS = UserService.obterUsuarioLogado();
        if(usuarioSS == null){
            logger.getLogger().error(Mensagens.MSG_USUARIO_NAO_LOGADO);
            throw new OperacaoNaoRealizadaException("Não foi possível fazer o refresh do token. Motivo : {}".replace("{}",Mensagens.MSG_USUARIO_NAO_LOGADO));
        }
        String token = jwtUtil.geraToken(usuarioSS.getUsername());
        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("access-control-expose-headers","Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/esqueci_senha")
    public ResponseEntity<Void> geraNovaSenha(@Valid @RequestBody EmailDTO emailDTO){
        logger.getLogger().info(Mensagens.MSG_REQUISICAO_CRIACAO_OBJETO,getClass().getSimpleName());
        authService.envidaNovaSenha(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
