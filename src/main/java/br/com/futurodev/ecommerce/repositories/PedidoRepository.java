package br.com.futurodev.ecommerce.repositories;

import br.com.futurodev.ecommerce.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByNomeClienteContainingIgnoreCaseOrderByIdDesc(String nomeCliente);

    @Query("SELECT pi.pedido FROM PedidoItem pi WHERE UPPER(pi.produto.nome) LIKE UPPER(:nomeProduto) ORDER BY pi.pedido.id DESC")
    List<Pedido> findByItemProdutoNome(String nomeProduto);

    @Query(value = "SELECT * FROM pedido WHERE qtd_itens BETWEEN :qtd1 AND :qtd2 ORDER BY pedido_id DESC", nativeQuery = true)
    List<Pedido> findByQtdBetween(Integer qtd1, Integer qtd2);

}
