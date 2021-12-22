package br.com.aurock.crusobackend.repository;

import br.com.aurock.crusobackend.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Integer> {
}
