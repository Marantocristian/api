package com.example.api.controller;

import com.example.api.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    void debeCalcularDescuentoExitosamente() throws Exception {
        when(productoService.aplicarDescuento(100.0, 10.0)).thenReturn(90.0);

        mockMvc.perform(get("/api/productos/descuento")
                        .param("precio", "100.0")
                        .param("porcentaje", "10.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.precioOriginal").value(100.0))
                .andExpect(jsonPath("$.descuentoPorcentaje").value(10.0))
                .andExpect(jsonPath("$.precioFinal").value(90.0));
    }

    @Test
    void debeListarProductosExitosamente() throws Exception {
        when(productoService.listarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
