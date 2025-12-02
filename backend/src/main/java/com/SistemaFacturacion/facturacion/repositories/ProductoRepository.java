package com.SistemaFacturacion.facturacion.repositories;

import com.SistemaFacturacion.facturacion.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
