package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.dto.CidadeDTO;
import br.com.aurock.crusobackend.domain.dto.EstadoDTO;
import br.com.aurock.crusobackend.service.CidadeService;
import br.com.aurock.crusobackend.service.EstadoService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoECidadeResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    private final Log loggerEstadoResource = new Log(this);

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listarEstados(){
        loggerEstadoResource.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM,getClass().getSimpleName());
        return ResponseEntity.ok().body(estadoService.obterEstados());
    }

    @GetMapping(value = "/{id}/cidades")
    public ResponseEntity<List<CidadeDTO>> listarCidadesDeEstadosPorId(@PathVariable Integer id){
        loggerEstadoResource.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA,id,getClass().getSimpleName());
        return ResponseEntity.ok().body(cidadeService.obterCidadesPorIdEstado(id));
    }
}
