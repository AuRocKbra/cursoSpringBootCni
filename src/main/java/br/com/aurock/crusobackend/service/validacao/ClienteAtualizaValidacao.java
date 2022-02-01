package br.com.aurock.crusobackend.service.validacao;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.domain.dto.ClienteDTO;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.repository.handler.CamposMensagemValidacao;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteAtualizaValidacao implements ConstraintValidator<ClienteAtualiza, ClienteDTO> {

    @Override
    public void initialize(ClienteAtualiza constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    private final Log loggerCLienteAtualizaValidacao = new Log(this);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        loggerCLienteAtualizaValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_DADOS,getClass().getSimpleName(),clienteDTO.getEmail());
        List<CamposMensagemValidacao> erros = new ArrayList<>();

        @SuppressWarnings("unchecked")
        Map<String,String > mapRequest = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer idCliente = Integer.parseInt(mapRequest.get("id"));

        Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());
        if(cliente != null && !cliente.getId().equals(idCliente)){
            loggerCLienteAtualizaValidacao.getLogger().info(Mensagens.MSG_VALIDACAO_RESULTADO,getClass().getSimpleName(),clienteDTO.getEmail(),false);
            erros.add(new CamposMensagemValidacao("email","O email informado já existe em nossa base!"));
        }

        for (CamposMensagemValidacao campos : erros){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(campos.getMensagem()).addPropertyNode(campos.getNome()).addConstraintViolation();
        }

        return erros.isEmpty();
    }
}
