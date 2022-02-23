package br.com.aurock.crusobackend.service.exceptions;

public class AmazonException extends RuntimeException{

    public AmazonException (String mensagem){
        super(mensagem);
    }

    public AmazonException( String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
