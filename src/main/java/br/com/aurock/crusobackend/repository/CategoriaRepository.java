package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria,Integer> {
}
