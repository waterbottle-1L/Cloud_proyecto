package com.SistemaFacturacion.facturacion.repositories;

import com.SistemaFacturacion.facturacion.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
