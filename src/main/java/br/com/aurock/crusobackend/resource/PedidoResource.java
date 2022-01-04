package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Pedido;
import br.com.aurock.crusobackend.service.PedidoService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
