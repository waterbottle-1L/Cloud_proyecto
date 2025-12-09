package com.SistemaFacturacion.facturacion.controllers;

import com.SistemaFacturacion.facturacion.dtos.CrearProductoDTO;
import com.SistemaFacturacion.facturacion.dtos.ProductoResponseDTO;
import com.SistemaFacturacion.facturacion.dtos.ProductoSummaryDTO;
import com.SistemaFacturacion.facturacion.dtos.UpdateProductoDTO;
import com.SistemaFacturacion.facturacion.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody @Valid CrearProductoDTO crearProductoDTO){
        ProductoResponseDTO productoResponseDTO = productoService.create(crearProductoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductoSummaryDTO>> getAllProducts(@PageableDefault(size=10, page = 0)
                                                                   Pageable pageable){
        return ResponseEntity.ok(productoService.findAll(pageable)); // 200 status code
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductoResponseDTO> updateProducto(@PathVariable Long id,
                                                              @RequestBody @Valid UpdateProductoDTO updateProductoDTO){
        return ResponseEntity.ok(productoService.update(id, updateProductoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable Long id){
        productoService.delete(id);
        return ResponseEntity.noContent().build(); // 204 status code
    }

}
