package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.domain.DTO.CategoriaDTO;
import br.com.aurock.crusobackend.service.CategoriaService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    private final Log logRecursoCategoria = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Integer id){
        logRecursoCategoria.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getSimpleName());
        return ResponseEntity.ok().body(categoriaService.buscarCategoria(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>>  listarTodasCategorias(){
        logRecursoCategoria.getLogger().info(Mensagens.MSG_REQUISICAO_LISTAGEM,getClass().getSimpleName());
        List<Categoria> categorias = categoriaService.listarCategorias();
        List<CategoriaDTO> categoriaDTOS = categorias.stream().map(obj -> new CategoriaDTO(obj.getId(),obj.getNome())).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @PostMapping
    public ResponseEntity<Void> criarNovaCategoria(@RequestBody Categoria categoria){
        logRecursoCategoria.getLogger().info(Mensagens.MSG_REQUISICAO_CRIACAO_OBJETO,getClass().getSimpleName());
        Categoria novaCategoria = categoriaService.criaNovaCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(novaCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizaCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaAtualizada){
        logRecursoCategoria.getLogger().info(Mensagens.MSG_REQUISICAO_ATUALIZACAO_OBJETO,getClass().getSimpleName());
        categoriaService.atualizaCategoria(id,categoriaAtualizada);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletaCategoria(@PathVariable Integer id){
        logRecursoCategoria.getLogger().info(Mensagens.MSG_REQUISICAO_DELETE_OBJETO,getClass().getSimpleName());
        categoriaService.deletaCategoriaPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
