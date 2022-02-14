package br.com.aurock.crusobackend.security.exceptions;

import java.awt.event.FocusEvent;

public class FalhaNoProcessoDeAutenticacao extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public FalhaNoProcessoDeAutenticacao( String mensagem){
        super(mensagem);
    }

    public FalhaNoProcessoDeAutenticacao (String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
