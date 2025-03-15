package com.wishlist.thiagolenz.wishlist.produtos.api;

import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/v1")
public class ProdutosAPI {
    @PostMapping
    public ProdutoDTO create (@RequestBody ProdutoDTO produto) {
        return null;
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO update (@RequestBody ProdutoDTO produto) {
        return null;
    }

    @GetMapping("/cliente/{clienteId}")
    public List<ProdutoDTO> getAllByClienteId (@PathVariable("clienteId") Long clienteId) {
        return null;
    }

    @GetMapping("/cliente/{clienteId}/search")
    public Optional<ProdutoDTO> getByClienteAndNomeProduto (
            @PathVariable("clienteId") Long clienteId,
            @RequestParam("nomeProduto") String nomeProduto) {
        //TODO VALIDAR SE nomeProduto foi informado
        return null;
    }

    @DeleteMapping("/{produtoId}")
    public void deleteByProdutoId (@PathVariable("produtoId") Long produtoId) {
        //TODO delete não foi implementado
    }
}
