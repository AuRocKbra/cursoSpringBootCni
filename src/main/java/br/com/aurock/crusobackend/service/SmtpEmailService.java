package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.service.exceptions.EmailNaoEnviadoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class SmtpEmailService extends AbastractEmailService{

    @Autowired
    private MailSender mailSender;

    private Log loggerSmtpEmailServce = new Log(this);

    @Override
    public void enviaEmail(SimpleMailMessage mensagem) {
        try {
            loggerSmtpEmailServce.getLogger().info("Enviando email!");
            mailSender.send(mensagem);
            loggerSmtpEmailServce.getLogger().info("Email enviado!");
        }catch (MailException e) {
            loggerSmtpEmailServce.getLogger().info(Mensagens.MSG_ERRO_ENVIO_EMAIL);
            throw new EmailNaoEnviadoException(Mensagens.MSG_ERRO_ENVIO_EMAIL);
        }
    }
}
