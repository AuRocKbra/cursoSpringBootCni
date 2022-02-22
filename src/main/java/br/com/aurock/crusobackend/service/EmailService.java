package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void enviaEmailConfirmacaoPedido(Pedido pedido);

    void enviaEmail(SimpleMailMessage mensagem);

    void enviaEmailHtmlConfirmacaoPedido(Pedido pedido);

    void enviaEmailHtml(MimeMessage mensagem);

    void enviaEmailNovaSenha (Cliente cliente, String senha);
}
