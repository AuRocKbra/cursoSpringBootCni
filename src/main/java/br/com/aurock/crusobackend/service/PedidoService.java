package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Pedido;
import br.com.aurock.crusobackend.repository.PedidoRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private final Log logPedidoService = new Log(this);

    public Pedido obterDadosPedidoPorId(Integer id){
        logPedidoService.getLogger().info(Mensagens.MSG_PEDIDO_SERVICE_BUSCA_ID,id);
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        logPedidoService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,pedido.isPresent());
        return pedido.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO));
    }
}
