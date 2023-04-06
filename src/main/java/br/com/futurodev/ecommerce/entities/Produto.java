package br.com.futurodev.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long id;

    @Column(length = 120, nullable = false)
    private String nome;

    private String descricao;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(precision = 19, scale = 3)
    private BigDecimal qtdEstoque;

}
