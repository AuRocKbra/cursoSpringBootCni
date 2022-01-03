package br.com.aurock.crusobackend.service.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public OperacaoNaoPermitidaException(String msg){
        super(msg);
    }

    public OperacaoNaoPermitidaException(String msg, Throwable e){
        super(msg,e);
    }
}
