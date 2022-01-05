package br.com.aurock.crusobackend.domain.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O campo nome é de preenchimento obrigatório")
    @Length(min=5, max = 80, message = "O nome da categoria deve conter entre 5 à 80 caracters")
    private String nome;
    private Integer id;

    public CategoriaDTO( Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }
}
