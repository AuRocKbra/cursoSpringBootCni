package br.com.aurock.crusobackend.domain.DTO;

import br.com.aurock.crusobackend.util.Mensagens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = Mensagens.MSG_CAMPO_OBRIGATORIO)
    @Email(message = Mensagens.MSG_VALIDACAO_EMAIL)
    private String email;
}
