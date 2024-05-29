package com.codigo.proyecto_bootcamp.domain.ports.in;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ProductoRequest;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceIn {
    ProductoDto crearProductoIn(ProductoRequest productoRequest);
    Optional<ProductoDto> buscarProductoporIdIn(Long id);
    List<ProductoDto> buscartodosProductosIn();
    ProductoDto actualizarProductoIn(Long id, ProductoRequest productoRequest);
    ProductoDto borrarProductoIn(Long id);
}
