package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido,Integer> {
}
