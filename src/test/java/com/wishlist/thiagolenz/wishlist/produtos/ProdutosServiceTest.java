package com.wishlist.thiagolenz.wishlist.produtos;

import com.wishlist.thiagolenz.wishlist.exception.LimiteProdutosException;
import com.wishlist.thiagolenz.wishlist.exception.RegistroDuplicadoException;
import com.wishlist.thiagolenz.wishlist.produtos.model.ProdutoEntity;
import com.wishlist.thiagolenz.wishlist.produtos.services.ProdutosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProdutosServiceTest {

    ProdutosService service = new ProdutosService();

    @Test
    void validar_cenario_criar_registro_sucesso() {
        ProdutoEntity request = new ProdutoEntity();
        ProdutoEntity result = service.create(request);
        assertNotNull(result);
    }

    @Test
    void validar_cenario_atualizar_registro_sucesso() {
        ProdutoEntity existente = new ProdutoEntity();
        ProdutoEntity result = service.update(existente);
        assertNotNull(result);
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
