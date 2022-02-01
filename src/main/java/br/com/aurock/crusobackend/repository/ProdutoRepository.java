package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer> {
    /*Com JPQL*/
//    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//    Page<Produto> listaProdutoPaginadaComCategoria(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

    /*Com padrão de nome JPA do spring*/
    @Transactional
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,List<Categoria> categorias,Pageable pageRequest);
}
