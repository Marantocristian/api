package com.example.api.controller;

import com.example.api.model.Producto;
import com.example.api.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @GetMapping("/descuento")
    public ResponseEntity<Map<String, Object>> calcularDescuento(
            @RequestParam double precio,
            @RequestParam double porcentaje) {
        double resultado = productoService.aplicarDescuento(precio, porcentaje);
        return ResponseEntity.ok(Map.of(
                "precioOriginal", precio,
                "descuentoPorcentaje", porcentaje,
                "precioFinal", resultado
        ));
    }
}
