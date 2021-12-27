package br.com.aurock.crusobackend.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EstadoPagamento {

    PENDENTE (1,"Pendente"),
    QUITADO (2,"Quitado"),
    CANCELADO (3,"Cancelado");

    private int codigo;
    private String descricao;

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(EstadoPagamento estado : EstadoPagamento.values()){
            if(cod.equals(estado.getCodigo())){
                return estado;
            }
        }
        throw new IllegalArgumentException("Código do estodo do pagamento inválido!");
    }
}
