package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final Log logClienteService = new Log(this);

    public Cliente obterDadosCliente(Integer id){
        logClienteService.getLogger().info(Mensagens.MSG_CLIENTE_SERVICE_BUSCA_ID,id);
        Optional<Cliente> cliente = clienteRepository.findById(id);
        logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,cliente.isPresent());
        return cliente.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null));
    }
}
