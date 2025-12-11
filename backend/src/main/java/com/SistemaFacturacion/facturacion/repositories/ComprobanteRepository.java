package com.SistemaFacturacion.facturacion.repositories;

import com.SistemaFacturacion.facturacion.entities.ComprobanteVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<ComprobanteVenta, Long> {
}
