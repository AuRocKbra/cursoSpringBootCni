package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviaEmailConfirmacaoPedido(Pedido pedido);

    void enviaEmail(SimpleMailMessage mensagem);
}
