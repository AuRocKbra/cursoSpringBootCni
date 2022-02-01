package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.PagamentoBoleto;
import br.com.aurock.crusobackend.domain.Pedido;
import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import br.com.aurock.crusobackend.repository.PedidoRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    private final Log logPedidoService = new Log(this);

    public Pedido obterDadosPedidoPorId(Integer id){
        logPedidoService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA_ID,getClass().getSimpleName(),id);
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        logPedidoService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,pedido.isPresent());
        return pedido.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO));
    }

    public Pedido criaPedido( Pedido novoPedido){
        logPedidoService.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO,getClass().getSimpleName());
        novoPedido.setId(null);
        novoPedido.setInstante(new Date());
        novoPedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        novoPedido.getPagamento().setPedido(novoPedido);
        if(novoPedido.getPagamento() instanceof PagamentoBoleto){
            PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) novoPedido.getPagamento();
            boletoService.criaBoleto(pagamentoBoleto,novoPedido.getInstante());
        }
        novoPedido = pedidoRepository.save(novoPedido);
        
    }
}
