package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository <Estado,Integer> {
}
