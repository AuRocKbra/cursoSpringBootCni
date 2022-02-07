package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Pedido;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Date;

public abstract class AbastractEmailService implements EmailService{

    @Value("${default.sender}")
    private String remetente;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviaEmailConfirmacaoPedido(Pedido pedido) {
        SimpleMailMessage msg = criaMsgEmailPedido(pedido);
        enviaEmail(msg);
    }

    protected SimpleMailMessage criaMsgEmailPedido(Pedido pedido){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(pedido.getCliente().getEmail());
        message.setFrom(remetente);
        message.setSubject(Mensagens.MSG_TITULO_EMAIL_CONFIRMACAO_PEDIDO +pedido.getId());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(pedido.toString());
        return message;
    }

    protected String htmlParaTemplatePedido(Pedido pedido){
        Context context = new Context();
        context.setVariable("pedido",pedido);
        return templateEngine.process("email/templateEmalConfirmaPedido",context);
    }

    @Override
    public void enviaEmailHtmlConfirmacaoPedido(Pedido pedido){
        try {
            MimeMessage mensagem = criaMsgHtmlEmailPedido(pedido);
            enviaEmailHtml(mensagem);
        }catch (MessagingException e ){
            enviaEmailConfirmacaoPedido(pedido);
        }
    }

    protected MimeMessage criaMsgHtmlEmailPedido(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(pedido.getCliente().getNome());
        mimeMessageHelper.setFrom(remetente);
        mimeMessageHelper.setSubject(Mensagens.MSG_TITULO_EMAIL_CONFIRMACAO_PEDIDO + pedido.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlParaTemplatePedido(pedido),true);
        return mimeMessage;
    }
}
