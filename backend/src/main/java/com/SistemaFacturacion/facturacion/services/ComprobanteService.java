package com.SistemaFacturacion.facturacion.services;


import com.SistemaFacturacion.facturacion.dtos.comprobante.CrearComprobanteDTO;
import com.SistemaFacturacion.facturacion.dtos.comprobante.ItemComprobanteDTO;
import com.SistemaFacturacion.facturacion.entities.*;
import com.SistemaFacturacion.facturacion.repositories.ClienteRepository;
import com.SistemaFacturacion.facturacion.repositories.ComprobanteRepository;
import com.SistemaFacturacion.facturacion.repositories.ProductoRepository;
import com.SistemaFacturacion.facturacion.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ComprobanteService {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final ComprobanteRepository comprobanteRepository;

    public ComprobanteService(ProductoRepository productoRepository,
                              UsuarioRepository usuarioRepository,
                              ClienteRepository clienteRepository,
                              ComprobanteRepository comprobanteRepository)
    {
        this.clienteRepository = clienteRepository;
        this.comprobanteRepository = comprobanteRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ComprobanteVenta crearComprobante(CrearComprobanteDTO dto){
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cliente no encontrado: " + dto.clienteId()));

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cajero no encontrado: " + dto.usuarioId()));

        ComprobanteVenta comprobante = new ComprobanteVenta();
        comprobante.setTipoComprobante(dto.tipoComprobante());
        comprobante.setTipoMoneda(dto.tipoMoneda());
        comprobante.setMetodoPago(dto.metodoPago());
        comprobante.setCliente(cliente);
        comprobante.setUsuario(usuario);

        for (ItemComprobanteDTO item: dto.items()){
            Producto producto = productoRepository.findById(item.productoId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Producto no encontrado: " + item.productoId()));
            // Validar stock
            long actualStock = producto.getStock() == null ? 0L : producto.getStock();
            DetalleComprobante detalle = getDetalleComprobante(item, producto, actualStock);
            comprobante.addDetalle(detalle);

            // Reducir stock y persistir producto (productoRepo.save se hará al final por flush o explícito)
            producto.setStock(actualStock - item.cantidad());
            productoRepository.save(producto);
        }

        // recalcular totales (ya lo hace addDetalle, pero por seguridad)
        comprobante.recomputeTotales();

        // Guardar comprobante (cascade guardará detalles)
        return comprobanteRepository.save(comprobante);
    }

    private static DetalleComprobante getDetalleComprobante(ItemComprobanteDTO item, Producto producto, long actualStock) {
        if (item.cantidad() == null || item.cantidad() <= 0) {
            throw new IllegalArgumentException("Cantidad inválida para producto " + producto.getId());
        }
        if (actualStock < item.cantidad()) {
            throw new IllegalArgumentException("Stock insuficiente para producto " + producto.getId());
        }

        // precio a usar: si en DTO viene precioUnitario lo usamos; si no, usamos precioUnitario del producto
        BigDecimal precioUnitario = item.precioUnitario() != null ? item.precioUnitario() : producto.getPrecioUnitario();

        DetalleComprobante detalle = new DetalleComprobante(producto, item.cantidad(), precioUnitario);
        return detalle;
    }


}
