package br.com.aurock.crusobackend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@Entity
@Getter
@Setter

public class Pedido implements Serializable {

    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega")
    private Endereco enderecoDeEntrega;

    @OneToMany(mappedBy = "itemPedidoPK.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public double getValorTotal(){
        double soma = 0;
        for(ItemPedido item : itens){
            soma += item.getSubTotal();
        }
        return soma;
    }

    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido de número:");
        builder.append(getId());
        builder.append("\nData de realização do pedido: ");
        builder.append(sdf.format(getInstante()));
        builder.append("\nCliente:\n");
        builder.append(getCliente().getNome());
        builder.append("\nSituação do pagamento: ");
        builder.append(getPagamento().getEstadoPagamento().getDescricao());
        builder.append("\nItens do pedido:");
        for(ItemPedido item : getItens()){
            builder.append("\n");
            builder.append(item.toString());
        }
        builder.append("\nValor total do pedido: ");
        builder.append(numberFormat.format(getValorTotal()));
        return builder.toString();
    }
}
