package br.com.futurodev.ecommerce.controllers;

import br.com.futurodev.ecommerce.entities.Pedido;
import br.com.futurodev.ecommerce.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> get() {
        return pedidoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Pedido pedido) {
        try {
            pedido.setId(null);
            pedido = pedidoService.salvar(pedido);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
