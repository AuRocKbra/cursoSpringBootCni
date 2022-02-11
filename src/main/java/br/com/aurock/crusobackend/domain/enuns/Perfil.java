package br.com.aurock.crusobackend.domain.enuns;

import br.com.aurock.crusobackend.util.Mensagens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Perfil {

    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENTE");

    private int cod;
    private String descricao;

    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Perfil x : Perfil.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException(Mensagens.MSG_ARGUMENTO_INVALIDO.replace("{}","perfil"));
    }
}
