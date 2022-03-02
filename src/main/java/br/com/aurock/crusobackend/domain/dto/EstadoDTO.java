package br.com.aurock.crusobackend.domain.dto;

import br.com.aurock.crusobackend.domain.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public EstadoDTO(Estado estado){
        this.id = estado.getId();
        this.nome = estado.getNome();
    }
}
