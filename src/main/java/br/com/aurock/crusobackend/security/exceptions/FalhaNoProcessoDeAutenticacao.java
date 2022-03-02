package br.com.aurock.crusobackend.security.exceptions;

public class FalhaNoProcessoDeAutenticacao extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FalhaNoProcessoDeAutenticacao( String mensagem){
        super(mensagem);
    }

    public FalhaNoProcessoDeAutenticacao (String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
