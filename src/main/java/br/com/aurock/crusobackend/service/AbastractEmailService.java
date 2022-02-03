package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import javax.swing.*;
import java.util.Date;

public abstract class AbastractEmailService implements EmailService{

    @Value("${default.sender}")
    private String remetente;

    @Override
    public void enviaEmailConfirmacaoPedido(Pedido pedido) {
        SimpleMailMessage msg = criaMsgEmailPedido(pedido);
        enviaEmail(msg);
    }

    protected SimpleMailMessage criaMsgEmailPedido(Pedido pedido){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(pedido.getCliente().getEmail());
        message.setFrom(remetente);
        message.setSubject("Confirmação do pedido de número "+pedido.getId());
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(pedido.toString());
        return message;
    }
}
