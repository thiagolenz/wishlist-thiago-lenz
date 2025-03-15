package com.wishlist.thiagolenz.wishlist.produtos.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;

    @NotNull
    private String nomeProduto;

    @NotNull
    private Long clienteId;

    @NotNull
    private BigDecimal preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nomeProduto, Long clienteId, BigDecimal preco) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.clienteId = clienteId;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
