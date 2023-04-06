package br.com.futurodev.ecommerce.services;

import br.com.futurodev.ecommerce.entities.Pedido;
import br.com.futurodev.ecommerce.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) throws Exception {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return pedido.get();
        }
        throw new Exception("Pedido não encontrado!");
    }

    public Pedido salvar(Pedido pedido) throws Exception {

        if (pedido.getNomeCliente() == null || pedido.getNomeCliente().isEmpty())
            throw new Exception("Nome do cliente é obrigatório!");

        if (pedido.getId() != null) {
            throw new Exception("Pedido não pode ser alterado!");
        }

        return pedidoRepository.save(pedido);
    }

}
