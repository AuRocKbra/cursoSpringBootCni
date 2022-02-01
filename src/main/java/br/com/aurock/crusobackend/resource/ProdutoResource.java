package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Produto;
import br.com.aurock.crusobackend.domain.dto.ProdutoDTO;
import br.com.aurock.crusobackend.resource.utils.Conversor;
import br.com.aurock.crusobackend.service.ProdutoService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    private final Log loggerProdutoResource = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(@PathVariable Integer id){
        loggerProdutoResource.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getSimpleName());
        return ResponseEntity.ok().body(produtoService.obterProdutoPorId(id));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ProdutoDTO>> listaProdutoPaginadaComCategoria(
        @RequestParam(value = "nome", defaultValue = "") String nome,
        @RequestParam(value = "categorias", defaultValue = "") String categorias,
        @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(value = "linhasPorPagina",defaultValue = "24") Integer linhasPorPagina,
        @RequestParam(value = "ordenadoPor", defaultValue = "nome")String ordenadoPor,
        @RequestParam(value = "ordem",defaultValue = "ASC")String ordem
    ){
        loggerProdutoResource.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM_PAGINADA,getClass().getSimpleName());
        List<Integer> listaIdsCategoria = Conversor.converteStringParaInteger(categorias);
        String nomeDecode = Conversor.decodificaString(nome);
        Page<Produto> produtos = produtoService.listaProdutoPaginadaComCategoria(nomeDecode,listaIdsCategoria,pagina,linhasPorPagina,ordenadoPor, ordem);
        Page<ProdutoDTO> produtoDTOS = produtos.map(obj->new ProdutoDTO(obj.getId(),obj.getNome(),obj.getPreco()));
        return ResponseEntity.ok().body(produtoDTOS);
    }
}
