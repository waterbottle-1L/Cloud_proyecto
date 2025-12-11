package com.SistemaFacturacion.facturacion.controllers;


import com.SistemaFacturacion.facturacion.dtos.comprobante.CrearComprobanteDTO;
import com.SistemaFacturacion.facturacion.entities.ComprobanteVenta;
import com.SistemaFacturacion.facturacion.services.ComprobanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    public ComprobanteController(ComprobanteService comprobanteService){
        this.comprobanteService = comprobanteService;
    }

    @PostMapping
    public ResponseEntity<ComprobanteVenta> crearComprobante(@RequestBody CrearComprobanteDTO dto) {
        ComprobanteVenta saved = comprobanteService.crearComprobante(dto);
        return ResponseEntity.ok(saved);
    }
}
