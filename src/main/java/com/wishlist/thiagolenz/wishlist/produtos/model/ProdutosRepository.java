package com.wishlist.thiagolenz.wishlist.produtos.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutosRepository extends MongoRepository<ProdutoEntity, String> {
    Optional<ProdutoEntity> findByNomeProdutoAndClienteId(String produtoNome, Long clienteId);

    List<ProdutoEntity> findByClienteId(Long clienteId);
}
