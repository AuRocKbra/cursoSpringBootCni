package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository <Pagamento, Integer> {
}
