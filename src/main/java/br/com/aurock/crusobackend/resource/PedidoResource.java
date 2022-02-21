package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Pedido;
import br.com.aurock.crusobackend.service.PedidoService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    private final Log logPedidoResource = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> obterDadosPedidoPorId(@PathVariable Integer id){
        logPedidoResource.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getSimpleName());
        return ResponseEntity.ok().body(pedidoService.obterDadosPedidoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> criaPedido(@RequestBody Pedido novoPedido){
        logPedidoResource.getLogger().info(Mensagens.MSG_REQUISICAO_CRIACAO_OBJETO,getClass().getSimpleName());
        novoPedido = pedidoService.criaPedido(novoPedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(novoPedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> listarPedidosPaginada(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhas", defaultValue = "24") Integer linhas,
            @RequestParam(value = "ordenadoPor", defaultValue = "instante") String ordenadoPor,
            @RequestParam(value = "ordem", defaultValue = "DESC") String ordem){
        logPedidoResource.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM_PAGINADA,getClass().getSimpleName());
        Page<Pedido> list = pedidoService.obterPedidosDoCliente(pagina,linhas,ordenadoPor,ordem);
        return ResponseEntity.ok().body(list);
    }
}
