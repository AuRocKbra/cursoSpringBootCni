package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.DTO.ClienteDTO;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoPermitidaException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_ATUALIZA_OBJETO,getClass().getSimpleName(),id);
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

    public Page<Cliente> listaClientePaginada(Integer pagina, Integer linhasPorPagina, String ordenadoPor, String ordem){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_LISTAR_PAGINADA_OBJETO,getClass().getSimpleName());
        PageRequest pageRequest = PageRequest.of(pagina,linhasPorPagina, Sort.Direction.valueOf(ordem),ordenadoPor);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente converteParaCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(),null,null);
        return cliente;
    }

    public void deletaClientePorId(Integer id){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_DELETA_OBJETO,getClass().getSimpleName(),id);
        obterDadosCliente(id);
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new OperacaoNaoPermitidaException(Mensagens.MSG_OPERACAO_NAO_PERMITIDA,e.getCause());
        }
    }
}
