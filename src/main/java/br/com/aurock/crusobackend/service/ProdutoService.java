package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.domain.Produto;
import br.com.aurock.crusobackend.repository.CategoriaRepository;
import br.com.aurock.crusobackend.repository.ProdutoRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final Log loggerProdutoService = new Log(this);

    public Produto obterProdutoPorId(Integer id){
        loggerProdutoService.getLogger().info(Mensagens.MSG_SERVICE_BUSCA_ID,getClass().getSimpleName(),id);
        Optional<Produto> produto = produtoRepository.findById(id);
        loggerProdutoService.getLogger().info(Mensagens.MSG_RESULTADO_BUSCA_ID,id,produto.isPresent());
        return produto.orElseThrow(()->new ObjetoNaoEncontradoException(Mensagens.MSG_OBJECTO_NAO_ENCONTRADO));
    }

    public Page<Produto> listaProdutoPaginadaComCategoria(String nome, List<Integer> idsCategorias, Integer pagina, Integer linhasPorPagina, String ordenadoPor, String ordem){
        loggerProdutoService.getLogger().info(Mensagens.MSG_SERVICE_LISTAR_PAGINADA_OBJETO,getClass().getSimpleName());
        PageRequest pageRequest = PageRequest.of(pagina,linhasPorPagina, Sort.Direction.valueOf(ordem),ordenadoPor);
        List<Categoria> categorias = categoriaRepository.findAllById(idsCategorias);
        return produtoRepository.listaProdutoPaginadaComCategoria(nome,categorias, pageRequest);
    }
}
