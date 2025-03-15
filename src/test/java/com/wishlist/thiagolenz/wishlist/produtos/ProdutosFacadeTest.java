package com.wishlist.thiagolenz.wishlist.produtos;

import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import com.wishlist.thiagolenz.wishlist.produtos.facade.ProdutosFacade;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.services.ProdutosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutosFacadeTest {
    @Mock
    ProdutosService service;

    @InjectMocks
    ProdutosFacade facade;

    @Test
    void produto_facade_create() {
        ProdutoDTO request = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        ProdutoEntity response = new ProdutoEntity(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        when(service.create(any())).thenReturn(response);
        ProdutoDTO produtoDTO = facade.create(request);
        assertEquals(produtoDTO.getNomeProduto(), request.getNomeProduto());
        assertEquals(produtoDTO.getClienteId(), request.getClienteId());
    }

    @Test
    void produto_facade_update() {
        ProdutoDTO request = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        ProdutoEntity response = new ProdutoEntity(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        when(service.update(any())).thenReturn(response);
        ProdutoDTO produtoDTO = facade.update(request);
        assertEquals(produtoDTO.getNomeProduto(), request.getNomeProduto());
        assertEquals(produtoDTO.getClienteId(), request.getClienteId());
    }

    @Test
    void produto_facade_get_by_client_id() {
        ProdutoEntity response = new ProdutoEntity(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        List<ProdutoEntity> result = new ArrayList<>();
        result.add(response);
        when(service.getAllByCliente(response.getClienteId())).thenReturn(result);
        List<ProdutoDTO> list = facade.getAllByClienteId(response.getClienteId());
        List<ProdutoDTO> listEmpty = facade.getAllByClienteId(2L);
        assertEquals(list.size(), result.size());
        assertEquals(0, listEmpty.size());
    }

    @Test
    void produto_facade_get_by_client_id_and_nome() {
        ProdutoEntity response = new ProdutoEntity(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        when(service.findByProdutoAndClientId(response.getClienteId(), response.getNomeProduto()))
                .thenReturn(Optional.of(response));
        Optional<ProdutoDTO> existent = facade.getByClienteAndNomeProduto(response.getClienteId(), response.getNomeProduto());
        assertTrue(existent.isPresent());

        Optional<ProdutoDTO> notExistent = facade.getByClienteAndNomeProduto(response.getClienteId(), "Geladeira");
        assertTrue(notExistent.isEmpty());
    }
}
