package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.DTO.ClienteDTO;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final Log logClienteService = new Log(this);

    public Cliente obterDadosCliente(Integer id){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA_ID,getClass().getSimpleName(),id);
        Optional<Cliente> cliente = clienteRepository.findById(id);
        logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,cliente.isPresent());
        return cliente.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null));
    }

    public List<Cliente> listarClientes(){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_LISTAR_OBJETO,getClass().getSimpleName());
        List<Cliente> clientes = clienteRepository.findAll();
        if(clientes.isEmpty()){
            throw new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null);
        }
        else {
            logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_LISTAGEM,true);
            return clientes;
        }
    }

    public Cliente atualizaCliente(Integer id, Cliente cliente){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_ATUALIZA_OBJETO.getClass().getSimpleName(),id);
        Cliente clienteAntigo = obterDadosCliente(id);
        try{
            clienteAntigo.setNome(cliente.getNome());
            clienteAntigo.setEmail(cliente.getEmail());
            cliente = clienteRepository.save(clienteAntigo);
            logClienteService.getLogger().info(Mensagens.MSG_SERVICE_ATUALIZA_OBJETO_RESULTADO,cliente,true);
            return cliente;
        }catch (RuntimeException e){
            throw new OperacaoNaoRealizadaException(Mensagens.MSG_OPERACAO_NAO_REALIZADA,e.getCause());
        }
    }

    public Cliente converteParaCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }
}
