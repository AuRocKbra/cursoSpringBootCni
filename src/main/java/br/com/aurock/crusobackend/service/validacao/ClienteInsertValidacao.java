package br.com.aurock.crusobackend.service.validacao;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.dto.ClienteNovoDTO;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.repository.handler.CamposMensagemValidacao;
import br.com.aurock.crusobackend.service.validacao.utils.ValidaCpfCnpj;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidacao implements ConstraintValidator<ClienteInsert, ClienteNovoDTO> {

    @Override
    public void initialize(ClienteInsert ann){ /* TODO document why this method is empty */ }

    private final Log loggerClienteInsertValidacao = new Log(this);

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public boolean isValid(ClienteNovoDTO novoCliente, ConstraintValidatorContext context){
        List<CamposMensagemValidacao> erros = new ArrayList<>();
        loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_DADOS,getClass().getSimpleName(),novoCliente.getCpfCnpj());
        if(novoCliente.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !ValidaCpfCnpj.isCpfValid(novoCliente.getCpfCnpj())){
            loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),novoCliente.getCpfCnpj(),false);
            erros.add(new CamposMensagemValidacao("cpfCnpj","O CPF informado não é válido!"));
        }
        if(novoCliente.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !ValidaCpfCnpj.isCnpjValid(novoCliente.getCpfCnpj())){
            loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),novoCliente.getCpfCnpj(),false);
            erros.add(new CamposMensagemValidacao("cpfCnpj","O CNPJ informado não é válido!"));
        }
        Cliente cliente = clienteRepository.findByEmail(novoCliente.getEmail());
        loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_DADOS,getClass().getSimpleName(),novoCliente.getEmail());
        if(cliente != null){
            loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),novoCliente.getEmail(),false);
            erros.add(new CamposMensagemValidacao("email","O email informado já existe em nossa base!"));
        }
        for(CamposMensagemValidacao campos : erros){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(campos.getMensagem()).addPropertyNode(campos.getNome()).addConstraintViolation();
        }
        return erros.isEmpty();
    }
}
