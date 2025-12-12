package com.SistemaFacturacion.facturacion.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comprobante")
public class ComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comprobante")
    private TipoComprobante tipoComprobante;

    @Enumerated(EnumType.STRING)
    @Column(name="moneda")
    private TipoMoneda tipoMoneda;

    @Column(name = "base_gravadas", nullable = false)
    private BigDecimal opGravadas = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal igv = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name="metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleComprobante> detalles = new ArrayList<>();

    public void addDetalle(DetalleComprobante detalle) {
        detalle.setComprobante(this);
        this.detalles.add(detalle);
        recomputeTotales();
    }

    public void removeDetalle(DetalleComprobante detalle) {
        detalle.setComprobante(null);
        this.detalles.remove(detalle);
        recomputeTotales();
    }

    public void recomputeTotales() {
        BigDecimal base = BigDecimal.ZERO;
        for (DetalleComprobante d : detalles) {
            if (d.getSubtotal() == null) d.calcularSubtotal();
            base = base.add(d.getSubtotal());
        }
        this.opGravadas = base;
        // Asumo IGV 18% (ajusta si tu país/constante es distinto)
        this.igv = base.multiply(new BigDecimal("0.18"));
        this.total = base.add(this.igv);
    }
}
