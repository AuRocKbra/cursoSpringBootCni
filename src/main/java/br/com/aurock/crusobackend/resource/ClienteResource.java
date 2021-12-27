package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.service.ClienteService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    private Log logResourceCliente = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obterDadosCliente(@PathVariable Integer id){
        logResourceCliente.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getName());
        return ResponseEntity.ok().body(clienteService.obterDadosCliente(id));
    }
}
