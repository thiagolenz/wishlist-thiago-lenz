package com.wishlist.thiagolenz.wishlist.produtos.services;

import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository repository;

    public ProdutoEntity create (ProdutoEntity produto) {
        ProdutoEntity result = repository.save(produto);
        return result;
    }

    public ProdutoEntity update (ProdutoEntity produto) {
        ProdutoEntity result = repository.save(produto);
        return result;
    }

    public List<ProdutoEntity> getAllByCliente (Long clienteId) {
        return null;
    }

    public Optional<ProdutoEntity> findByProdutoAndClientId(Long clienteId, String nomeProduto) {
        return Optional.empty();
    }
}
