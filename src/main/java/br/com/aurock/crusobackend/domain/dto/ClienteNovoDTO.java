package br.com.aurock.crusobackend.domain.dto;

import br.com.aurock.crusobackend.util.Mensagens;
import br.com.aurock.crusobackend.service.validacao.ClienteInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ClienteInsert
public class ClienteNovoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    @Length(min=5,max=180, message = Mensagens.MSG_TAMANHO_STRING_PART1+5+Mensagens.MSG_LIGACAO_FRASE+180+Mensagens.MSG_TAMANHO_STRING_PART2)
    private String nome;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    @Email(message = Mensagens.MSG_VALIDACAO_EMAIL)
    private String email;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    private String cpfCnpj;

    private Integer tipoCliente;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    private String logradouro;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    private String cep;

    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public ClienteNovoDTO(String nome, String email, String cpfCnpj, Integer tipoCliente, String logradouro, String numero, String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3, Integer cidadeId) {
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.tipoCliente = tipoCliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.cidadeId = cidadeId;
    }
}
