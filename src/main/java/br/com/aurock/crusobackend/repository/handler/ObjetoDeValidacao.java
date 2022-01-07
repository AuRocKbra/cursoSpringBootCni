package br.com.aurock.crusobackend.repository.handler;

import java.util.ArrayList;
import java.util.List;

public class ObjetoDeValidacao extends ObjetoErro{

    private static final long serialVersionUID = 1L;

    private List<CamposMensagemValidacao> erros = new ArrayList<>();

    public ObjetoDeValidacao(Integer statusErro, String msg, Long timeStamp) {
        super(statusErro, msg, timeStamp);
    }

    public List<CamposMensagemValidacao> getErros(){
        return erros;
    }

    public void setErros(String nome, String mensagem){
        erros.add(new CamposMensagemValidacao(nome,mensagem));
    }
}
