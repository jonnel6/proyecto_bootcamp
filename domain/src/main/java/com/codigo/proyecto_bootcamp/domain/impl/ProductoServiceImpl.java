package com.codigo.proyecto_bootcamp.domain.impl;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ProductoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.ProductoServiceIn;
import com.codigo.proyecto_bootcamp.domain.ports.out.ProductoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProductoServiceImpl implements ProductoServiceIn {

    public final ProductoServiceOut productoServiceOut;
    @Override
    public ProductoDto crearProductoIn(ProductoRequest productoRequest) {
        return productoServiceOut.crearProductoOut(productoRequest);
    }

    @Override
    public Optional<ProductoDto> buscarProductoporIdIn(Long id) {
        return productoServiceOut.buscarProductoporIdOut(id);
    }

    @Override
    public List<ProductoDto> buscartodosProductosIn() {
        return productoServiceOut.buscartodosProductosOut();
    }

    @Override
    public ProductoDto actualizarProductoIn(Long id, ProductoRequest productoRequest) {
        return productoServiceOut.actualizarProductoOut(id, productoRequest);
    }

    @Override
    public ProductoDto borrarProductoIn(Long id) {
        return productoServiceOut.borrarProductoOut(id);
    }
}
