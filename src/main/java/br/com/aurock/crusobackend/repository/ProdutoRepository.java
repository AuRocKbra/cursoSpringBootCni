package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer> {
}
