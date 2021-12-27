package br.com.aurock.crusobackend.domain;

import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PagamentoCartao extends Pagamento{

    private static final Long serialVersionUID = 1l;

    private Integer quantidadeParcelas;

    public PagamentoCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer quantidadeParcelas) {
        super(id, estadoPagamento, pedido);
        this.quantidadeParcelas = quantidadeParcelas;
    }


}
