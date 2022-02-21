package br.com.aurock.crusobackend.service.exceptions;

public class OperacaoNaoAutorizadaException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public OperacaoNaoAutorizadaException(String msg){
        super(msg);
    }

    public OperacaoNaoAutorizadaException(String msg, Throwable e){
        super(msg,e);
    }
}
