package com.wishlist.thiagolenz.wishlist.produtos.services;

import com.wishlist.thiagolenz.wishlist.exception.LimiteProdutosException;
import com.wishlist.thiagolenz.wishlist.exception.RegistroDuplicadoException;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutosService {
    @Value("#{new Integer('${wishlist.limit.produtos}')}")
    private final Integer limiteProdutos = 20;

    @Autowired
    private ProdutosRepository repository;

    public ProdutoEntity create (ProdutoEntity produto) {
        validarLimiteRegistros(produto.getClienteId());
        validarProdutoDuplicadoCriacao(produto);
        produto.setId(UUID.randomUUID().toString());
        ProdutoEntity result = repository.save(produto);
        return result;
    }

    private void validarLimiteRegistros(Long clienteId) {
        if (repository.findByClienteId(clienteId).size() == limiteProdutos) {
            throw new LimiteProdutosException("Limite de produtos excedido:", limiteProdutos);
        }
    }

    private void validarProdutoDuplicadoCriacao(ProdutoEntity produto) {
        Optional<ProdutoEntity> existente = repository.findByNomeProdutoAndClienteId(produto.getNomeProduto(), produto.getClienteId());
        if (existente.isPresent())
            throw new RegistroDuplicadoException("Já existe um produto para esse cliente com esse nome", produto);
    }

    public ProdutoEntity update (ProdutoEntity produto) {
        validarProdutoDuplicadoUpdate(produto);
        ProdutoEntity result = repository.save(produto);
        return result;
    }

    private void validarProdutoDuplicadoUpdate(ProdutoEntity produto) {
        Optional<ProdutoEntity> existente = repository.findByNomeProdutoAndClienteId(produto.getNomeProduto(), produto.getClienteId());
        if (existente.isPresent() && Objects.equals(existente.get().getId(), produto.getId())) {
            throw new RegistroDuplicadoException("Já existe um produto para esse cliente com esse nome", produto);
        }
    }

    public List<ProdutoEntity> getAllByCliente (Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Optional<ProdutoEntity> findByProdutoAndClientId(Long clienteId, String nomeProduto) {
        return repository.findByNomeProdutoAndClienteId(nomeProduto, clienteId);
    }

    public boolean deleteById (String id) {
        Optional<ProdutoEntity> existente = repository.findById(id);
        if (existente.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
