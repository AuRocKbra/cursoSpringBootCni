package br.com.aurock.crusobackend.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TipoCliente {

    PESSOAFISICA(1,"Pessoa física"),
    PESSOAJURIDICA(2,"Pessoa jurídica");

    private int cod;
    private String descricao;

    public static TipoCliente toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(TipoCliente x : TipoCliente.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Id de tipo pessoa inválido");
    }
}
