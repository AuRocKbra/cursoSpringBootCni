package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Integer> {
}
