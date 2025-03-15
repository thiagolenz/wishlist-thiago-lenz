package com.wishlist.thiagolenz.wishlist.produtos;

import com.wishlist.thiagolenz.wishlist.produtos.api.ProdutosAPI;
import com.wishlist.thiagolenz.wishlist.produtos.dto.ProdutoDTO;
import com.wishlist.thiagolenz.wishlist.produtos.facade.ProdutosFacade;
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
public class ProdutosAPITest {
    @Mock
    ProdutosFacade facade;

    @InjectMocks
    ProdutosAPI api;

    @Test
    void produto_api_create() {
        ProdutoDTO request = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        ProdutoDTO response = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        when(facade.create(any())).thenReturn(response);
        ProdutoDTO produtoDTO = api.create(request);
        assertEquals(produtoDTO.getNomeProduto(), request.getNomeProduto());
        assertEquals(produtoDTO.getClienteId(), request.getClienteId());
    }

    @Test
    void produto_api_update() {
        ProdutoDTO request = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        ProdutoDTO response = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        when(facade.update(any())).thenReturn(response);
        ProdutoDTO produtoDTO = api.update(request);
        assertEquals(produtoDTO.getNomeProduto(), request.getNomeProduto());
        assertEquals(produtoDTO.getClienteId(), request.getClienteId());
    }

    @Test
    void produto_api_get_by_client_id() {
        ProdutoDTO response = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        List<ProdutoDTO> result = new ArrayList<>();
        result.add(response);
        when(facade.getAllByClienteId(response.getClienteId())).thenReturn(result);
        List<ProdutoDTO> list = api.getAllByClienteId(response.getClienteId());
        List<ProdutoDTO> listEmpty = api.getAllByClienteId(2L);
        assertEquals(list.size(), result.size());
        assertEquals(0, listEmpty.size());
    }

    @Test
    void produto_api_get_by_client_id_and_nome() {
        ProdutoDTO response = new ProdutoDTO(
                "1",
                "Mouse",
                1L,
                new BigDecimal(100)
        );
        when(facade.getByClienteAndNomeProduto(response.getClienteId(), response.getNomeProduto()))
                .thenReturn(Optional.of(response));
        Optional<ProdutoDTO> existent = api.getByClienteAndNomeProduto(response.getClienteId(), response.getNomeProduto());
        assertTrue(existent.isPresent());

        Optional<ProdutoDTO> notExistent = api.getByClienteAndNomeProduto(response.getClienteId(), "Geladeira");
        assertTrue(notExistent.isEmpty());
    }
}
