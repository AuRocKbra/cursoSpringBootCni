package br.com.aurock.crusobackend.domain.dto;

import br.com.aurock.crusobackend.domain.Cidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CidadeDTO(Cidade cidade){
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }
}
