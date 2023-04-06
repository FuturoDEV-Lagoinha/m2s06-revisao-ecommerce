package br.com.futurodev.ecommerce.controllers;

import br.com.futurodev.ecommerce.entities.Produto;
import br.com.futurodev.ecommerce.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> get() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Produto produto) {
        try {
            produto.setId(null);
            produto = produtoService.salvar(produto);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            if (id == null)
                throw new Exception("Id é obrigatório!");
            produto.setId(id);
            produto = produtoService.salvar(produto);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (id == null)
                throw new Exception("Id é obrigatório!");
            produtoService.apagar(id);
            return ResponseEntity.ok("Produto excluído com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
