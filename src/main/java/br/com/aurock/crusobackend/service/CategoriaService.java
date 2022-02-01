package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.domain.dto.CategoriaDTO;
import br.com.aurock.crusobackend.repository.CategoriaRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoPermitidaException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final Log logCategoriaService = new Log(this);

    public Categoria buscarCategoria(Integer id){
        logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA_ID,getClass().getSimpleName(),id);
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        logCategoriaService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,categoria.isPresent());
        return categoria.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null));
    }

    public Categoria criaNovaCategoria(Categoria categoria){
        logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO,getClass().getSimpleName());
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
        logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_ATUALIZA_OBJETO,getClass().getSimpleName(),id);
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

    public void deletaCategoriaPorId(Integer id){
        logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_DELETA_OBJETO,getClass().getSimpleName(),id);
        buscarCategoria(id);
        try {
            categoriaRepository.deleteById(id);
            logCategoriaService.getLogger().info(Mensagens.MSG_RESULTADO_DELETA,id,true);
        }catch (DataIntegrityViolationException e){
            logCategoriaService.getLogger().info(Mensagens.MSG_RESULTADO_DELETA,id,false);
            throw new OperacaoNaoPermitidaException(Mensagens.MSG_OPERACAO_NAO_PERMITIDA,e.getCause());
        }
    }

    public List<Categoria> listarCategorias(){
        logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_LISTAR_OBJETO,getClass().getSimpleName());
        List<Categoria> categorias = categoriaRepository.findAll();
        if(categorias.isEmpty()){
            throw new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO,null);
        }
        else{
            logCategoriaService.getLogger().info(Mensagens.MSG_RESULTADO_LISTAGEM,true);
            return categorias;
        }
    }

    public Page<Categoria> listaCategoriaPaginada(Integer pagina, Integer linhasPorPagina, String ordenadoPor, String ordem){
        logCategoriaService.getLogger().info(Mensagens.MSG_SERVICE_LISTAR_PAGINADA_OBJETO,getClass().getSimpleName());
        PageRequest pageRequest = PageRequest.of(pagina,linhasPorPagina, Sort.Direction.valueOf(ordem),ordenadoPor);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria converterParaCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());
        return categoria;
    }
}
