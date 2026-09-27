package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void debeCalcularPrecioConDescuento() {
        double resultado = productoService.aplicarDescuento(100.0, 10);
        assertEquals(90.0, resultado);
    }

    @Test
    void debeLanzarExcepcionConDescuentoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            productoService.aplicarDescuento(100.0, -5);
        });
    }
}
