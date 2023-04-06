package br.com.futurodev.ecommerce.repositories;

import br.com.futurodev.ecommerce.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByNome(String nome);

    Boolean existsByNomeAndIdNot(String nome, Long id);

}
