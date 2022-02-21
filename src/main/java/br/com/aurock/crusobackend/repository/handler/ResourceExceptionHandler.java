package br.com.aurock.crusobackend.repository.handler;

import br.com.aurock.crusobackend.security.exceptions.FalhaNoProcessoDeAutenticacao;
import br.com.aurock.crusobackend.service.exceptions.*;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ObjetoDeValidacao> erroDeValidacaoException(MethodArgumentNotValidException e, HttpServletRequest request){
        ObjetoDeValidacao erro = new ObjetoDeValidacao(HttpStatus.BAD_REQUEST.value(), Mensagens.MSG_VALIDACAO_CAMPO,System.currentTimeMillis());
        for(FieldError er: e.getBindingResult().getFieldErrors()){
            erro.setErros(er.getField(),er.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EmailNaoEnviadoException.class)
    public ResponseEntity<ObjetoErro> emailNaoEnviadoException(EmailNaoEnviadoException e, HttpServletRequest request){
        ObjetoErro erro = new ObjetoErro(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(FalhaNoProcessoDeAutenticacao.class)
    public ResponseEntity<ObjetoErro> falhaNoProcessoDeAutenticacao(FalhaNoProcessoDeAutenticacao e, HttpServletRequest request){
        ObjetoErro erro = new ObjetoErro(HttpStatus.UNAUTHORIZED.value(), e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }

    @ExceptionHandler(OperacaoNaoAutorizadaException.class)
    public ResponseEntity<ObjetoErro> operacaoNaoAutorizada(OperacaoNaoAutorizadaException e, HttpServletRequest request){
        ObjetoErro erro = new ObjetoErro(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }
}
