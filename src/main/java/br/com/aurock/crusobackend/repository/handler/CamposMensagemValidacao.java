package br.com.aurock.crusobackend.repository.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CamposMensagemValidacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String mensagem;

    public CamposMensagemValidacao(String nome, String mensagem){
        this.nome = nome;
        this.mensagem = mensagem;
    }
}
