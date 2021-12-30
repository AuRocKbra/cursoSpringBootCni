package br.com.aurock.crusobackend.service.exceptions;

public class OperacaoNaoRealizadaException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public OperacaoNaoRealizadaException(String msg){
        super(msg);
    }

    public OperacaoNaoRealizadaException(String msg, Throwable cause){
        super(msg,cause);
    }
}
