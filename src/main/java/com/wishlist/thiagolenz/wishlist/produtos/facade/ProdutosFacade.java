package com.wishlist.thiagolenz.wishlist.produtos.facade;

import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosFacade {

    public ProdutoDTO create(ProdutoDTO produto) {
        return null;
    }

    public ProdutoDTO update(ProdutoDTO produto) {
        return null;
    }

    public List<ProdutoDTO> getAllByClienteId(Long clienteId) {
        return null;
    }

    public Optional<ProdutoDTO> getByClienteAndNomeProduto(Long clienteId, String nomeProduto) {
        return null;
    }

    public void deleteByProdutoId(Long produtoId) {
    }
}
