package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.repository.CategoriaRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
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
        logCategoriaService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,categoria.isPresent());
        return categoria.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null));
    }

    public Categoria criaNovaCategoria(Categoria categoria){
        logCategoriaService.getLogger().info(Mensagens.MSG_CATEGORIA_SERVICE_CRIA_OBJETO);
        try {
            categoria.setId(null);
            Categoria novaCategoria = categoriaRepository.save(categoria);
            logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO_RESULTADO,novaCategoria, true);
            return novaCategoria;
        }catch (RuntimeException e){
            throw new OperacaoNaoRealizadaException(Mensagens.MSG_OPERACAO_NAO_REALIZADA,e.getCause());
        }
    }

    public Categoria atualizaCategoria(Integer id, Categoria categoria){
        logCategoriaService.getLogger().info(Mensagens.MSG_CATEGORIA_SERVICE_ATUALIZA_OBJETO,id);
        Categoria categoriaAntiga = buscarCategoria(id);
        try{
            categoriaAntiga.setNome(categoria.getNome());
            categoria = categoriaRepository.save(categoriaAntiga);
            logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_ATUALIZA_OBJETO_RESULTADO,categoria,true);
            return categoria;
        }catch (RuntimeException e){
            throw new OperacaoNaoRealizadaException(Mensagens.MSG_OPERACAO_NAO_REALIZADA,e.getCause());
        }
    }
}
