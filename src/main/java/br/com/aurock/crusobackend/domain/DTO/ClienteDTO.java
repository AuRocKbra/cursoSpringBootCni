package br.com.aurock.crusobackend.domain.DTO;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.util.Mensagens;
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
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    @Length(min=5,max=120,message = Mensagens.MSG_TAMANHO_STRING_PART1+"5"+Mensagens.MSG_LIGACAO_FRASE+"120"+Mensagens.MSG_TAMANHO_STRING_PART2)
    private String nome;
    @NotEmpty(message = Mensagens.MSG_CAMPO_VAZIO)
    @Email(message = Mensagens.MSG_VALIDACAO_EMAIL)
    private String email;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }
}
