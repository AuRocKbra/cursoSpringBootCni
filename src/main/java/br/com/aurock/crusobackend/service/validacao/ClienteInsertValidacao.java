package br.com.aurock.crusobackend.service.validacao;

import br.com.aurock.crusobackend.domain.DTO.ClienteNovoDTO;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.handler.CamposMensagemValidacao;
import br.com.aurock.crusobackend.service.validacao.utils.ValidaCpfCnpj;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidacao implements ConstraintValidator<ClienteInsert, ClienteNovoDTO> {

    @Override
    public void initialize(ClienteInsert ann){}

    private Log loggerClienteInsertValidacao = new Log(this);

    @Override
    public boolean isValid(ClienteNovoDTO novoCliente, ConstraintValidatorContext context){
        List<CamposMensagemValidacao> erros = new ArrayList<>();
        loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_DADOS,getClass().getSimpleName(),novoCliente.getCpfCnpj());
        if(novoCliente.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !ValidaCpfCnpj.isCpfValid(novoCliente.getCpfCnpj())){
            loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),novoCliente.getCpfCnpj(),false);
            erros.add(new CamposMensagemValidacao("cpfCnpj","O CPF informado não é válido!"));
        }
        else{
            if(novoCliente.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !ValidaCpfCnpj.isCnpjValid(novoCliente.getCpfCnpj())){
                loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),novoCliente.getCpfCnpj(),false);
                erros.add(new CamposMensagemValidacao("cpfCnpj","O CNPJ informado não é válido!"));
            }
        }
        for(CamposMensagemValidacao campos : erros){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(campos.getMensagem()).addPropertyNode(campos.getNome()).addConstraintViolation();
        }
        loggerClienteInsertValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),novoCliente.getCpfCnpj(),true);
        return erros.isEmpty();
    }
}
