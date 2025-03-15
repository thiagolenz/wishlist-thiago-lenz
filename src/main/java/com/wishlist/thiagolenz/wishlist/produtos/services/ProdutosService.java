package com.wishlist.thiagolenz.wishlist.produtos.services;

import com.wishlist.thiagolenz.wishlist.exception.RegistroDuplicadoException;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository repository;

    public ProdutoEntity create (ProdutoEntity produto) {
        validarProdutoDuplicadoCriacao(produto);
        ProdutoEntity result = repository.save(produto);
        return result;
    }

    private void validarProdutoDuplicadoCriacao(ProdutoEntity produto) {
        Optional<ProdutoEntity> existente = repository.findByProdutoNomeAndClienteId(produto.getNomeProduto(), produto.getClienteId());
        if (existente.isPresent())
            throw new RegistroDuplicadoException("Já existe um produto para esse cliente com esse nome ");
    }

    public ProdutoEntity update (ProdutoEntity produto) {
        validarProdutoDuplicadoUpdate(produto);
        ProdutoEntity result = repository.save(produto);
        return result;
    }

    private void validarProdutoDuplicadoUpdate(ProdutoEntity produto) {
        Optional<ProdutoEntity> existente = repository.findByProdutoNomeAndClienteId(produto.getNomeProduto(), produto.getClienteId());
        if (existente.isPresent() && Objects.equals(existente.get().getId(), produto.getId())) {
            throw new RegistroDuplicadoException("Já existe um produto para esse cliente com esse nome ");
        }
    }

    public List<ProdutoEntity> getAllByCliente (Long clienteId) {
        return null;
    }

    public Optional<ProdutoEntity> findByProdutoAndClientId(Long clienteId, String nomeProduto) {
        return Optional.empty();
    }
}
