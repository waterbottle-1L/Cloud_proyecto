package com.SistemaFacturacion.facturacion.dtos.comprobante;

import com.SistemaFacturacion.facturacion.entities.MetodoPago;
import com.SistemaFacturacion.facturacion.entities.TipoComprobante;
import com.SistemaFacturacion.facturacion.entities.TipoMoneda;

import java.util.List;


public record  CrearComprobanteDTO (
        TipoComprobante tipoComprobante,
        TipoMoneda tipoMoneda,
        MetodoPago metodoPago,
        Long clienteId,
        Long usuarioId,
        List<ItemComprobanteDTO> items){
}
