package com.wishlist.thiagolenz.wishlist.produtos.api;

import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import com.wishlist.thiagolenz.wishlist.produtos.facade.ProdutosFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/v1")
public class ProdutosAPI {
    @Autowired private ProdutosFacade facade;

    @PostMapping
    public ProdutoDTO create (@Valid @RequestBody ProdutoDTO produto) {
        return facade.create(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO update (@Valid @RequestBody ProdutoDTO produto) {
        return facade.update(produto);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<ProdutoDTO> getAllByClienteId (@PathVariable("clienteId") Long clienteId) {
        return facade.getAllByClienteId(clienteId);
    }

    @GetMapping("/cliente/{clienteId}/search")
    public Optional<ProdutoDTO> getByClienteAndNomeProduto (
            @PathVariable("clienteId") Long clienteId,
            @RequestParam("nomeProduto") String nomeProduto) {
        //TODO VALIDAR SE nomeProduto foi informado
        return facade.getByClienteAndNomeProduto(clienteId, nomeProduto);
    }

    @DeleteMapping("/{produtoId}")
    public void deleteByProdutoId (@PathVariable("produtoId") String produtoId) {
        facade.deleteByProdutoId(produtoId);
    }
}
