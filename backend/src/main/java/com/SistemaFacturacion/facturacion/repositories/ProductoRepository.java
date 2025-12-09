package com.SistemaFacturacion.facturacion.repositories;

import com.SistemaFacturacion.facturacion.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombreProducto);
    boolean existsByNombre(String nombreProducto);
}
