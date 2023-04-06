package br.com.futurodev.ecommerce.services;

import br.com.futurodev.ecommerce.entities.Produto;
import br.com.futurodev.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) throws Exception {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return produto.get();
        }
        throw new Exception("Produto não encontrado!");
    }

    public Produto salvar(Produto produto) throws Exception {

        if (produto.getNome() == null || produto.getNome().isEmpty())
            throw new Exception("Nome é obrigatório!");

        if (produto.getPreco() == null)
            throw new Exception("Preço é obrigatório!");

        if (produto.getId() == null) {
            // Incluido
            if (produtoRepository.existsByNome(produto.getNome())) {
                throw new Exception("Produto já cadastrado!");
            }
        } else {
            // Editando
            if (!produtoRepository.existsById(produto.getId())) {
                throw new Exception("Produto não encontrado!");
            }
            if (produtoRepository.existsByNomeAndIdNot(produto.getNome(), produto.getId())) {
                throw new Exception("Produto já cadastrado!");
            }
        }

        return produtoRepository.save(produto);
    }

    public void apagar(Long id) throws Exception {
        Produto produto = buscarPorId(id);
        try {
            produtoRepository.delete(produto);
        } catch (Exception e) {
            throw new Exception("Houve algum problema!");
        }

    }

}
