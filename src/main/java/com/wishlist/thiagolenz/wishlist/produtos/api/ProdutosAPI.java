package com.wishlist.thiagolenz.wishlist.produtos.api;

import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import com.wishlist.thiagolenz.wishlist.produtos.facade.ProdutosFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/v1")
@Tag(name = "Produtos", description = "API para cadastro CRUD de produtos")
public class ProdutosAPI {
    @Autowired private ProdutosFacade facade;

    @PostMapping
    @Operation(
            summary = "Cadastra um novo Produto",
            description = "Operação para cadastrar um novo produto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    })
    public ProdutoDTO create (@Valid @RequestBody ProdutoDTO produto) {
        return facade.create(produto);
    }

    @Operation(
            summary = "Atualizar um Produto",
            description = "Operação para atualizar um produto existente na base ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    })
    @PutMapping("/{produtoId}")
    public ProdutoDTO update (@Valid @RequestBody ProdutoDTO produto) {
        return facade.update(produto);
    }

    @Operation(
            summary = "Produtos de um cliente",
            description = "Busca e retorna todos os produtos de um deterninado cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    })
    @GetMapping("/cliente/{clienteId}")
    public List<ProdutoDTO> getAllByClienteId (@PathVariable("clienteId") Long clienteId) {
        return facade.getAllByClienteId(clienteId);
    }

    @Operation(
            summary = "Pesquisa um produto",
            description = "Busca um produto por nome exato, existir retorna o objeto, caso contrário retorna null")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    })
    @GetMapping("/cliente/{clienteId}/search")
    public Optional<ProdutoDTO> getByClienteAndNomeProduto (
            @PathVariable("clienteId") Long clienteId,
            @RequestParam("nomeProduto") String nomeProduto) {
        return facade.getByClienteAndNomeProduto(clienteId, nomeProduto);
    }

    @Operation(
            summary = "Exclui um produto",
            description = "Baseado em um id existente exclui o produto da base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    })
    @DeleteMapping("/{produtoId}")
    public void deleteByProdutoId (@PathVariable("produtoId") String produtoId) {
        facade.deleteByProdutoId(produtoId);
    }
}
