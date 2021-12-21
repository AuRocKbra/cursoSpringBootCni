package br.com.aurock.crusoBeckEnd.service;

import br.com.aurock.crusoBeckEnd.domain.Categoria;
import br.com.aurock.crusoBeckEnd.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarCategoria(Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }
}
