package br.com.futurodev.ecommerce.services;

import br.com.futurodev.ecommerce.entities.Pedido;
import br.com.futurodev.ecommerce.entities.PedidoItem;
import br.com.futurodev.ecommerce.entities.Produto;
import br.com.futurodev.ecommerce.repositories.PedidoRepository;
import br.com.futurodev.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

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

    public List<Pedido> busca(String cliente, String nomeProduto, Integer qtd1, Integer qtd2) {
        if (cliente != null && !cliente.isEmpty()) {
            return pedidoRepository.findByNomeClienteContainingIgnoreCaseOrderByIdDesc(cliente);
        }
        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            return pedidoRepository.findByItemProdutoNome("%" + nomeProduto + "%");
        }
        if (qtd1 != null && qtd2 != null) {
            return pedidoRepository.findByQtdBetween(qtd1, qtd2);
        }
        return new ArrayList<>();
    }

    public Pedido salvar(Pedido pedido) throws Exception {

        if (pedido.getId() != null) {
            throw new Exception("Pedido não pode ser alterado!");
        }

        if (pedido.getNomeCliente() == null || pedido.getNomeCliente().isEmpty())
            throw new Exception("Nome do cliente é obrigatório!");

        pedido.setDataPedido(new Date());
        pedido.setValorTotal(BigDecimal.ZERO);
        pedido.setQtdItens(0);

        for (PedidoItem item : pedido.getItens()) {

            item.setPedido(pedido);

            Produto produto = produtoService.buscarPorId(item.getProduto().getId());
            item.setProduto(produto);
            item.setValorUnitario(produto.getPreco());
            item.setValorTotal(item.getQtd().multiply(produto.getPreco()));

            pedido.setValorTotal(pedido.getValorTotal().add(item.getValorTotal()));
            pedido.setQtdItens(pedido.getQtdItens() + 1);

        }

        return pedidoRepository.save(pedido);
    }

}
