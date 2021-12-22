package br.com.aurock.crusobackend.service.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public ObjetoNaoEncontradoException(String msg){
        super(msg);
    }

    public ObjetoNaoEncontradoException (String msg, Throwable cause){
        super(msg,cause);
    }
}
