package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.DTO.CategoriaDTO;
import br.com.aurock.crusobackend.domain.DTO.ClienteDTO;
import br.com.aurock.crusobackend.service.ClienteService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    private Log logResourceCliente = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obterDadosCliente(@PathVariable Integer id){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getSimpleName());
        return ResponseEntity.ok().body(clienteService.obterDadosCliente(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM,getClass().getSimpleName());
        List<Cliente> clientes = clienteService.listarClientes();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> listarTodasCategoriasPaginado(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina",defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordenadoPor", defaultValue = "nome")String ordenadoPor,
            @RequestParam(value = "ordem",defaultValue = "ASC")String ordem){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM_PAGINADA,getClass().getSimpleName());
        Page<Cliente> clientes = clienteService.listaCategoriaPaginada(pagina,linhasPorPagina,ordenadoPor,ordem);
        Page<ClienteDTO> clienteDTOPage = clientes.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(clienteDTOPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizaCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_ATUALIZACAO_OBJETO,getClass().getSimpleName());
        clienteService.atualizaCliente(id,clienteService.converteParaCliente(clienteDTO));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Integer id){
        logResourceCliente.getLogger().info(Mensagens.MSG_SERVICE_DELETA_OBJETO,getClass().getSimpleName());
        clienteService.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }
}
