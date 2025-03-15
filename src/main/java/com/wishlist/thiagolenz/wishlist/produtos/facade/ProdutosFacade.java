package com.wishlist.thiagolenz.wishlist.produtos.facade;

import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.services.ProdutosService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosFacade {
    @Autowired private ProdutosService service;

    public ProdutoDTO create(ProdutoDTO produto) {
        ProdutoEntity entidade = convertToEntity(produto);
        entidade = service.create(entidade);
        return convertToDTO(entidade);
    }

    public ProdutoDTO update(ProdutoDTO produto) {
        ProdutoEntity entidade = convertToEntity(produto);
        entidade = service.update(entidade);
        return convertToDTO(entidade);
    }

    public List<ProdutoDTO> getAllByClienteId(Long clienteId) {
        List<ProdutoEntity> result = service.getAllByCliente(clienteId);
        return result.stream().map(this::convertToDTO).toList();
    }

    public Optional<ProdutoDTO> getByClienteAndNomeProduto(Long clienteId, @NotNull String nomeProduto) {
        Optional<ProdutoEntity> produto = service.findByProdutoAndClientId(clienteId, nomeProduto);
        return produto.map(this::convertToDTO);
    }

    public void deleteByProdutoId(Long produtoId) {
        service.deleteById(produtoId);
    }

    private ProdutoEntity convertToEntity(ProdutoDTO dto) {
        return new ProdutoEntity(
                dto.getId(),
                dto.getNomeProduto(),
                dto.getClienteId(),
                dto.getPreco()
        );
    }

    private ProdutoDTO convertToDTO(ProdutoEntity entidade) {
        return new ProdutoDTO(
                entidade.getId(),
                entidade.getNomeProduto(),
                entidade.getClienteId(),
                entidade.getPreco()
        );
    }
}
