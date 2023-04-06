package br.com.futurodev.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;

    @Column(nullable = false)
    private Date dataPedido;

    @Column(nullable = false)
    private String nomeCliente;

    private String enderecoEntrega;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private Integer qtdItens;

}
