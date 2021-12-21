package br.com.aurock.crusoBeckEnd.repository;

import br.com.aurock.crusoBeckEnd.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria,Integer> {
}
