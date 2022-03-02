package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Estado;
import br.com.aurock.crusobackend.domain.dto.CidadeDTO;
import br.com.aurock.crusobackend.domain.dto.EstadoDTO;
import br.com.aurock.crusobackend.repository.EstadoRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;


    private final Log loggerEstadoService = new Log(this);

    public List<EstadoDTO> obterEstados(){
        loggerEstadoService.getLogger().info(Mensagens.MSG_SERVICE_LISTAR_OBJETO,getClass().getSimpleName());
        List<Estado> estados = estadoRepository.findAllByOrderByNome();
        if(estados == null){
            loggerEstadoService.getLogger().info(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO);
            throw new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO);
        }
        loggerEstadoService.getLogger().info(Mensagens.MSG_RESULTADO_LISTAGEM,true);
        return converteListaEstadosParaListaEstadosDTO(estados);
    }


    private List<EstadoDTO> converteListaEstadosParaListaEstadosDTO(List<Estado> estados){
        loggerEstadoService.getLogger().info(Mensagens.MSG_COVERTENDO_VALOR,"objeto", "objetoDTO");
        return estados.stream().map(EstadoDTO::new).collect(Collectors.toList());
    }
}
