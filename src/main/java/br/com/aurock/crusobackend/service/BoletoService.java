package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.PagamentoBoleto;
import br.com.aurock.crusobackend.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void criaBoleto(PagamentoBoleto pagamentoBoleto, Date instante){
        Calendar novaInstancia = Calendar.getInstance();
        novaInstancia.setTime(instante);
        novaInstancia.add(Calendar.DAY_OF_MONTH,7);
        pagamentoBoleto.setDataVencimento(novaInstancia.getTime());
    }
}
