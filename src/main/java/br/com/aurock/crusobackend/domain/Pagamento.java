package br.com.aurock.crusobackend.domain;

import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Inheritance(strategy =  InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    private static final Long serialVersionUID = 1l;

    @Id
    private Integer id;
    private Integer estadoPagamento;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    @JsonIgnore
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estadoPagamento = (estadoPagamento == null) ? null : estadoPagamento.getCodigo();
        this.pedido = pedido;
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento){
        this.estadoPagamento = estadoPagamento.getCodigo();
    }

    public EstadoPagamento getEstadoPagamento(){
        return EstadoPagamento.toEnum(estadoPagamento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
