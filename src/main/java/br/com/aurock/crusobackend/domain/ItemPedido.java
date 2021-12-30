package br.com.aurock.crusobackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private ItemPedidoPK itemPedidoPK = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(Pedido pedido,Produto produto, Double desconto, Integer quantidade,Double preco){
        itemPedidoPK.setPedido(pedido);
        itemPedidoPK.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Pedido getPedido(){
        return itemPedidoPK.getPedido();
    }

    public Produto getProduto(){
        return itemPedidoPK.getProduto();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(itemPedidoPK, that.itemPedidoPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemPedidoPK);
    }
}
