package br.com.aurock.crusobackend.repository.handler;

import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoPermitidaException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ObjetoErro> objetoNaoEncontradoException (ObjetoNaoEncontradoException e, HttpServletRequest request){
        ObjetoErro erro = new ObjetoErro(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(OperacaoNaoRealizadaException.class)
    public ResponseEntity<ObjetoErro> operacaoNaoRealizadaException (OperacaoNaoRealizadaException e, HttpServletRequest request){
        ObjetoErro erro =  new ObjetoErro(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ResponseEntity<ObjetoErro> operacaoNaoPermitidaException(OperacaoNaoPermitidaException e, HttpServletRequest request){
        ObjetoErro erro = new ObjetoErro(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
