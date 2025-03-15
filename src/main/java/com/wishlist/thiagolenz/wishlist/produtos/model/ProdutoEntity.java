package com.wishlist.thiagolenz.wishlist.produtos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Objects;

@Document("produtos")
public class ProdutoEntity {
    @Id
    private String id;

    @Field("nome_produto")
    private String nomeProduto;

    @Field("cliente_id")
    private Long clienteId;

    @Field("preco")
    private BigDecimal preco;

    public ProdutoEntity() {
    }

    public ProdutoEntity(String id, String nomeProduto, Long clienteId, BigDecimal preco) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.clienteId = clienteId;
        this.preco = preco;
    }

    public ProdutoEntity(String nomeProduto, Long clienteId, BigDecimal preco) {
        this.nomeProduto = nomeProduto;
        this.clienteId = clienteId;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return Objects.equals(nomeProduto, that.nomeProduto) && Objects.equals(clienteId, that.clienteId) && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeProduto, clienteId, preco);
    }
}
