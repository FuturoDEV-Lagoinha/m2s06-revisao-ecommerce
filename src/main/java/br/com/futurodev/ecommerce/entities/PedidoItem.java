package br.com.futurodev.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Entity
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_item_id")
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(precision = 19, scale = 3, nullable = false)
    private BigDecimal qtd;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal valorUnitario;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal valorTotal;

}
