package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository <Cidade,Integer> {
}
