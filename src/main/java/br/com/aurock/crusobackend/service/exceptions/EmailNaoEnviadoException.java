package br.com.aurock.crusobackend.service.exceptions;

public class EmailNaoEnviadoException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public EmailNaoEnviadoException(String msg){
        super(msg);
    }

    public EmailNaoEnviadoException (String msg, Throwable cause){
        super(msg,cause);
    }
}
