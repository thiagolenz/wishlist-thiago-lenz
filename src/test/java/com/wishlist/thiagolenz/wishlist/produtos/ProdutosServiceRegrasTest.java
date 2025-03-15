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
public class ProdutosServiceRegrasTest {
    @Mock
    ProdutosRepository repository;

    @InjectMocks
    ProdutosService service;

    Long clienteId = 1L;

    @Test
    void validar_cenario_criar_registro_sucesso() {
        ProdutoEntity expectedResult = new ProdutoEntity(
                "1",
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
                "2",
                "Teclado",
                clienteId,
                new BigDecimal(200)
        );
        ProdutoEntity request = new ProdutoEntity(
                "2",
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
        ProdutoEntity existente = new ProdutoEntity(
                "3",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );
        ProdutoEntity novoRegistro = new ProdutoEntity(
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );
        when(repository.findByNomeProdutoAndClienteId(existente.getNomeProduto(), clienteId))
                .thenReturn(Optional.of(existente));

        assertThrows(
                RegistroDuplicadoException.class,
                () -> service.create(novoRegistro),
                "Esperado uma exception de registro duplicado na criacao"
        );
    }

    @Test
    void validar_cenario_atualizar_registro_duplicado_error() {
        ProdutoEntity existente = new ProdutoEntity(
                "3",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );

        ProdutoEntity atualizarRegistroErrado = new ProdutoEntity(
                "4",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );
        when(repository.findByNomeProdutoAndClienteId(existente.getNomeProduto(), clienteId))
                .thenReturn(Optional.of(existente));

        assertThrows(
                RegistroDuplicadoException.class,
                () -> service.create(atualizarRegistroErrado),
                "Esperado uma exception de registro duplicado no update"
        );
    }

    @Test
    void validar_cenario_criar_registro_limite_produtos_error() {
        List<ProdutoEntity> list20 = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            list20.add(new ProdutoEntity(
                    ""+i,
                    "Monitor",
                    clienteId,
                    new BigDecimal(1500 * i))
            );
        }

        when(repository.findByClienteId(clienteId)).thenReturn(list20);

        ProdutoEntity novoRegistroAlemDos20 = new ProdutoEntity(
                "4",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );

        assertThrows(
                LimiteProdutosException.class,
                () -> service.create(novoRegistroAlemDos20),
                "Esperado uma exception de limite de produtos"
        );
    }

    @Test
    void validar_cenario_buscar_todos_registros_sucesso() {
        List<ProdutoEntity> esperadosMock = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            esperadosMock.add(new ProdutoEntity(
                    ""+i,
                    "Monitor",
                    clienteId,
                    new BigDecimal(1500 * i))
            );
        }
        when(repository.findByClienteId(clienteId)).thenReturn(esperadosMock);
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
        ProdutoEntity existente = new ProdutoEntity(
                "3",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );
        when(repository.findByNomeProdutoAndClienteId(existente.getNomeProduto(), clienteId))
                .thenReturn(Optional.of(existente));

        Optional<ProdutoEntity> result = service.findByProdutoAndClientId(clienteId, existente.getNomeProduto());
        assertTrue(result.isPresent());
        assertEquals(result.get(), existente);
    }

    @Test
    void validar_cenario_excluir_registro_existente() {
        ProdutoEntity existente = new ProdutoEntity(
                "3",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );
        when(repository.findById(existente.getId())).thenReturn(Optional.of(existente));

        boolean excluiu = service.deleteById(existente.getId());
        assertTrue(excluiu);
    }

    @Test
    void validar_cenario_excluir_registro_nao_existente() {
        ProdutoEntity existente = new ProdutoEntity(
                "3",
                "Monitor",
                clienteId,
                new BigDecimal(1500)
        );
        boolean excluiu = service.deleteById(existente.getId());
        assertFalse(excluiu);
    }

}
