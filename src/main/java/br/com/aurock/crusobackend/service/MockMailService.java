package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.util.Log;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockMailService extends AbastractEmailService{

    private final Log logMockMailService = new Log(this);

    @Override
    public void enviaEmail(SimpleMailMessage mensagem) {
        logMockMailService.getLogger().info("Simulando envio de email....");
        logMockMailService.getLogger().info(mensagem.toString());
        logMockMailService.getLogger().info("Email enviado!");
    }

    @Override
    public void enviaEmailHtml(MimeMessage mensagem) {
        logMockMailService.getLogger().info("Simulando envio de email HTML....");
        logMockMailService.getLogger().info(mensagem.toString());
        logMockMailService.getLogger().info("Email enviado!");
    }
}
