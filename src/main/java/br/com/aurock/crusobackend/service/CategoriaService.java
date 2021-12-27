package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.repository.CategoriaRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
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
        logCategoriaService.getLogger().info(Mensagens.MSG_CATEGORIA_SERVICE_BUSCA_ID,id);
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        logCategoriaService.getLogger().info(Mensagens.MSG_CATEGORIA_RESULTADO_BUSCA_ID,id,categoria.isPresent());
        return categoria.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null));
    }
}
