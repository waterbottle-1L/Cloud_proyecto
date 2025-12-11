package com.SistemaFacturacion.facturacion.services;

import com.SistemaFacturacion.facturacion.dtos.producto.CrearProductoDTO;
import com.SistemaFacturacion.facturacion.dtos.producto.ProductoResponseDTO;
import com.SistemaFacturacion.facturacion.dtos.producto.ProductoSummaryDTO;
import com.SistemaFacturacion.facturacion.dtos.producto.UpdateProductoDTO;
import com.SistemaFacturacion.facturacion.entities.Producto;
import com.SistemaFacturacion.facturacion.repositories.ProductoRepository;
import com.SistemaFacturacion.facturacion.validators.ProductoValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final List<ProductoValidator> validators;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, List<ProductoValidator> validators){
        this.productoRepository = productoRepository;
        this.validators = validators;
    }

    @Transactional
    public ProductoResponseDTO create(CrearProductoDTO datos){
        validators.forEach(v-> v.Validate(datos));
        if (productoRepository.existsByNombre(datos.nombre())) {
            throw new IllegalArgumentException(("El producto ya existe"));
        }
        Producto producto = new Producto(datos);

        return new ProductoResponseDTO(productoRepository.save(producto));
    }

    @Transactional
    public Page<ProductoSummaryDTO> findAll(Pageable pageable){
        return productoRepository.findAll(pageable).map(ProductoSummaryDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductoSummaryDTO findById(Long id){
        Producto producto =productoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
                "Este ID para el usuario para el producto no fue encontrado: " + id
        )) ;

        return new ProductoSummaryDTO(producto);
    }

    @Transactional
    public ProductoResponseDTO update(Long id, UpdateProductoDTO datos){
        if(!productoRepository.existsById(id)){
            throw new EntityNotFoundException("Este id para el producto no fue encontrado");
        }

        Producto producto = productoRepository.getReferenceById(id);
        producto.update(datos);
        return new ProductoResponseDTO(producto);
    }

    @Transactional
    public void delete(Long id){
        if(!productoRepository.existsById(id)){
            throw new EntityNotFoundException("Este id para el producto no fue encontrado");
        }

        productoRepository.deleteById(id);
    }
}
