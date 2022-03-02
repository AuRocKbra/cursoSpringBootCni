package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cidade;
import br.com.aurock.crusobackend.domain.dto.CidadeDTO;
import br.com.aurock.crusobackend.repository.CidadeRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    private final Log loggerEstadoService = new Log(this);


    public List<CidadeDTO> obterCidadesPorIdEstado(Integer id){
        loggerEstadoService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA,getClass().getSimpleName(),"id",id);
        List<Cidade> cidades = cidadeRepository.recuperaCidadesPorIdEstado(id);
        if(cidades == null){
            loggerEstadoService.getLogger().info(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO);
            throw new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO);
        }
        loggerEstadoService.getLogger().info(Mensagens.MSG_RESULTADO_LISTAGEM,true);
        return converteCidadeParaCidadeDTO(cidades);
    }

    private List<CidadeDTO> converteCidadeParaCidadeDTO(List<Cidade> cidades){
        loggerEstadoService.getLogger().info(Mensagens.MSG_COVERTENDO_VALOR,"Cidade","CidadeDTO");
        return cidades.stream().map(CidadeDTO::new).collect(Collectors.toList());
    }
}
