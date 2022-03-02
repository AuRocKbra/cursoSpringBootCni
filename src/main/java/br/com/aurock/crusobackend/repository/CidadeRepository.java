package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository <Cidade,Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Cidade c WHERE c.estado.id = :idEstado ORDER BY c.nome")
    public List<Cidade> recuperaCidadesPorIdEstado(@Param("idEstado") Integer idEstado);
}
