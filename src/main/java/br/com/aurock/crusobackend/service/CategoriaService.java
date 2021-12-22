package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.repository.CategoriaRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final Log logCategoriaService = new Log(this);

    public Categoria buscarCategoria(Integer id){
        logCategoriaService.getLogger().info("Serviço de busca de categoria: Buscando por categoria de ID = {}",id);
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        logCategoriaService.getLogger().info("Resultado da busca por categoria de ID = {} -> Valor encontrado ? {}",id,categoria.isPresent());
        return categoria.orElseThrow(()->new ObjetoNaoEncontradoException("Nenhuma categoria foi encontrada para o número de ID informado",null));
    }
}
