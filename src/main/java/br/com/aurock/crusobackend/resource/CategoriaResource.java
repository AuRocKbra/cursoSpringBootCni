package br.com.aurock.crusobackend.resource;

import br.com.aurock.crusobackend.domain.Categoria;
import br.com.aurock.crusobackend.service.CategoriaService;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    private final Log logRecursoCategoria = new Log(this);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Integer id){
        logRecursoCategoria.getLogger().info(Mensagens.MSG_REQUISICAO_BUSCA_POR_ID,getClass().getName());
        return ResponseEntity.ok().body(categoriaService.buscarCategoria(id));
    }
}
