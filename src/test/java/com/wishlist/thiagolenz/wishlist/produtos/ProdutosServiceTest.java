package com.wishlist.thiagolenz.wishlist.produtos;

import com.wishlist.thiagolenz.wishlist.exception.LimiteProdutosException;
import com.wishlist.thiagolenz.wishlist.exception.RegistroDuplicadoException;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutosRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutosServiceTest {
    @Mock
    ProdutosRepository repository;

    @InjectMocks
    ProdutosService service;

    Long clienteId = 1L;

    @Test
    void validar_cenario_criar_registro_sucesso() {
        ProdutoEntity expectedResult = new ProdutoEntity(
                1L,
                "Mouse",
                clienteId,
                new BigDecimal(100)
        );
        ProdutoEntity request = new ProdutoEntity(
                "Mouse",
                clienteId,
                new BigDecimal(100)
        );
        when(repository.save(request)).thenReturn(expectedResult);

        ProdutoEntity result = service.create(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
        assertEquals(result, request);
    }

    @Test
    void validar_cenario_atualizar_registro_sucesso() {
        ProdutoEntity expectedResult = new ProdutoEntity(
                2L,
                "Teclado",
                clienteId,
                new BigDecimal(200)
        );
        ProdutoEntity request = new ProdutoEntity(
                2L,
                "Teclado",
                clienteId,
                new BigDecimal(200)
        );
        when(repository.save(request)).thenReturn(expectedResult);

        ProdutoEntity result = service.update(request);

        assertNotNull(result);
        assertEquals(result, expectedResult);
        assertEquals(result, request);
    }

    @Test
    void validar_cenario_criar_registro_duplicado_error() {
        ProdutoEntity existente = new ProdutoEntity();
        assertThrows(
                RegistroDuplicadoException.class,
                () -> service.create(existente),
                "Esperado uma exception de registro duplicado"
        );
    }

    @Test
    void validar_cenario_criar_registro_limite_produtos_error() {
        ProdutoEntity existente = new ProdutoEntity();
        assertThrows(
                LimiteProdutosException.class,
                () -> service.create(existente),
                "Esperado uma exception de limite de produtos"
        );
    }

    @Test
    void validar_cenario_buscar_todos_registros_sucesso() {
        Long clienteId = 1L;
        List<ProdutoEntity> esperadosMock = new ArrayList<>();
        List<ProdutoEntity> produtos = service.getAllByCliente(clienteId);
        assertNotNull(produtos);
        assertEquals(produtos.size(), esperadosMock.size());
    }

    @Test
    void validar_cenario_buscar_todos_registros_vazio() {
        Long clienteId = 1L;
        List<ProdutoEntity> esperadosMock = new ArrayList<>();
        List<ProdutoEntity> produtos = service.getAllByCliente(clienteId);
        assertNotNull(produtos);
        assertEquals(produtos.size(), esperadosMock.size());
    }

    @Test
    void validar_cenario_buscar_registro_por_cliente() {
        Long clienteId = 1L;
        ProdutoEntity produtoEsperadoMock = new ProdutoEntity();
        Optional<ProdutoEntity> result = service.findByProdutoAndClientId(clienteId, produtoEsperadoMock.getNomeProduto());
        assertTrue(result.isPresent());
    }

}
