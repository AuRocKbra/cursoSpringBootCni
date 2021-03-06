package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cidade;
import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.dto.ClienteDTO;
import br.com.aurock.crusobackend.domain.dto.ClienteNovoDTO;
import br.com.aurock.crusobackend.domain.Endereco;
import br.com.aurock.crusobackend.domain.enuns.Perfil;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.repository.EnderecoRepository;
import br.com.aurock.crusobackend.security.UsuarioSS;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoAutorizadaException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoPermitidaException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImagenService imagenService;

    @Value("${img.prefix.client.profile}")
    private String prefixoImagem;

    @Value("${img.profile.size}")
    private int tamanhoImagens;

    private final Log logClienteService = new Log(this);

    private final String [] comparadores = {"id","email"};

    private Map<String,String> valor = new HashMap<>();

    public Cliente obterDadosCliente(Integer id){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA,getClass().getSimpleName(),"id",id);
        valor.put(comparadores[0],String.valueOf(id));
        validaSeUsuarioLogado(valor);
        Optional<Cliente> cliente = clienteRepository.findById(id);
        logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA,comparadores[0],id,cliente.isPresent());
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

    public void deletaClientePorId(Integer id){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_DELETA_OBJETO,getClass().getSimpleName(),id);
        valor.put("id",String.valueOf(id));
        validaSeUsuarioLogado(valor);
        try{
            clienteRepository.deleteById(id);
            logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_DELETA,id,true);
        }catch (DataIntegrityViolationException e){
            logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_DELETA,id,false);
            throw new OperacaoNaoPermitidaException(Mensagens.MSG_OPERACAO_NAO_PERMITIDA,e.getCause());
        }
    }

    @Transactional
    public Cliente criaNovoCliente(Cliente novoCliente){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO, getClass().getSimpleName());
        try{
            clienteRepository.save(novoCliente);
            enderecoRepository.saveAll(novoCliente.getEnderecos());
            logClienteService.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO_RESULTADO,novoCliente.getClass().getSimpleName(),true);
            return novoCliente;
        }catch (RuntimeException e){
            throw new OperacaoNaoRealizadaException(Mensagens.MSG_OPERACAO_NAO_REALIZADA,e.getCause());
        }
    }

    public Cliente converteClienteDTOParaCliente(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(),null,null,null);
    }

    public Cliente converteClienteNovoDTOParaCliente(ClienteNovoDTO clienteNovoDTO){
        Cliente cliente = new Cliente(null,clienteNovoDTO.getNome(),clienteNovoDTO.getEmail(),clienteNovoDTO.getCpfCnpj(), TipoCliente.toEnum(clienteNovoDTO.getTipoCliente()), bCryptPasswordEncoder.encode(clienteNovoDTO.getSenha()));
        Cidade cidade = new Cidade(clienteNovoDTO.getCidadeId(),null,null);
        Endereco endereco = new Endereco(null,clienteNovoDTO.getLogradouro(),clienteNovoDTO.getNumero(),clienteNovoDTO.getComplemento(), clienteNovoDTO.getBairro(), clienteNovoDTO.getCep(), cliente,cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNovoDTO.getTelefone1());
        if(clienteNovoDTO.getTelefone2() != null){
            cliente.getTelefones().add(clienteNovoDTO.getTelefone2());
        }
        if(clienteNovoDTO.getTelefone3() != null){
            cliente.getTelefones().add(clienteNovoDTO.getTelefone3());
        }
        return cliente;
    }

    public URI uploadFotoPerfil(MultipartFile multipartFile){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO,getClass().getSimpleName());
        UsuarioSS usuarioSS = UserService.obterUsuarioLogado();
        logClienteService.getLogger().info(Mensagens.MSG_VALIDACAO_USUARIO);
        if(usuarioSS == null){
            logClienteService.getLogger().info(Mensagens.MSG_USUARIO_NAO_LOGADO);
            throw new OperacaoNaoAutorizadaException(Mensagens.MSG_ACESSO_NEGADO);
        }
        BufferedImage bufferedImage = imagenService.getImagemJpgDeArquivo(multipartFile);
        bufferedImage = imagenService.recortarImagem(bufferedImage);
        bufferedImage = imagenService.redimencionar(bufferedImage,tamanhoImagens);
        String arquivo = prefixoImagem + usuarioSS.getId() + ".jpg";
        return s3Service.uploadArquivo(imagenService.getImputStream(bufferedImage,"jpg"),arquivo,"image");
    }

    public Cliente obterDadosClientePorEmail(String email){
        logClienteService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA,getClass().getSimpleName(),"email",email);
        valor.put(comparadores[1],email);
        validaSeUsuarioLogado(valor);
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            logClienteService.getLogger().info(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO);
            throw new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null);
        }
        logClienteService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA,comparadores[1],email,true);
        return cliente;
    }

    private void validaSeUsuarioLogado(Map<String,String> valor){
        logClienteService.getLogger().info(Mensagens.MSG_VALIDACAO_USUARIO);
        UsuarioSS usuarioSS = UserService.obterUsuarioLogado();
        if(usuarioSS == null || (!usuarioSS.validaPerfil(Perfil.ADMIN) && !validaValorUsuario(valor,usuarioSS))){
            logClienteService.getLogger().info(Mensagens.MSG_USUARIO_NAO_LOGADO);
            throw new OperacaoNaoAutorizadaException("Acesso negado!");
        }
    }

    private boolean validaValorUsuario(Map<String,String>valor, UsuarioSS usuarioSS){
        if(valor.containsKey(comparadores[0])){
            return Integer.parseInt(valor.get(comparadores[0])) == usuarioSS.getId();
        }
        if(valor.containsKey(comparadores[1])){
            return valor.get(comparadores[1]).equals(usuarioSS.getUsername());
        }
        return false;
    }
}
