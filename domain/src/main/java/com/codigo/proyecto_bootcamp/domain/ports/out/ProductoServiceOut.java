package com.codigo.proyecto_bootcamp.domain.ports.out;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ProductoRequest;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceOut {

    ProductoDto crearProductoOut(ProductoRequest productoRequest);
    Optional<ProductoDto> buscarProductoporIdOut(Long id);
    List<ProductoDto> buscartodosProductosOut();
    ProductoDto actualizarProductoOut(Long id, ProductoRequest productoRequest);
    ProductoDto borrarProductoOut(Long id);
}
