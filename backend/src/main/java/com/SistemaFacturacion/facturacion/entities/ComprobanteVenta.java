package com.SistemaFacturacion.facturacion.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "comprobantes")
public class ComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_comprobante")
    private TipoComprobante tipoComprobante;

    @Column(name="moneda")
    private TipoMoneda tipoMoneda;

    @Column(name = "base_gravadas")
    private BigDecimal opGravadas;

    private BigDecimal igv;

    private BigDecimal total;

    @Column(name="metodo_pago")
    private MetodoPago metodoPago;
}
