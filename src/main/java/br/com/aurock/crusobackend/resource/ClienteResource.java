package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.dto.ClienteDTO;
import br.com.aurock.crusobackend.domain.dto.ClienteNovoDTO;
import br.com.aurock.crusobackend.service.ClienteService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    private final Log logResourceCliente = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obterDadosCliente(@PathVariable Integer id){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getSimpleName());
        return ResponseEntity.ok().body(clienteService.obterDadosCliente(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM,getClass().getSimpleName());
        List<Cliente> clientes = clienteService.listarClientes();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> listarClientesPaginado(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPagina",defaultValue = "24") Integer linhasPorPagina,
            @RequestParam(value = "ordenadoPor", defaultValue = "nome")String ordenadoPor,
            @RequestParam(value = "ordem",defaultValue = "ASC")String ordem){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM_PAGINADA,getClass().getSimpleName());
        Page<Cliente> clientes = clienteService.listaClientePaginada(pagina,linhasPorPagina,ordenadoPor,ordem);
        Page<ClienteDTO> clienteDTOPage = clientes.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(clienteDTOPage);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizaCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_ATUALIZACAO_OBJETO,getClass().getSimpleName());
        Cliente cliente = clienteService.converteClienteDTOParaCliente(clienteDTO);
        clienteService.atualizaCliente(id,cliente);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Integer id){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_DELETE_OBJETO,getClass().getSimpleName());
        clienteService.deletaClientePorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> criaNovoCliente(@Valid @RequestBody ClienteNovoDTO clienteNovoDTO){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_CRIACAO_OBJETO,getClass().getSimpleName());
        Cliente novoCliente = clienteService.converteClienteNovoDTOParaCliente(clienteNovoDTO);
        novoCliente = clienteService.criaNovoCliente(novoCliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(novoCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
