package br.com.aurock.crusobackend.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private Integer id;

    public CategoriaDTO( Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }
}
