package br.com.aurock.crusobackend.domain;

import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PagamentoBoleto extends Pagamento{

    private static final Long serialVersionUID = 1l;

    private Date dataPagamento;
    private Date dataVencimento;

    public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento){
        super(id,estadoPagamento,pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }


}
