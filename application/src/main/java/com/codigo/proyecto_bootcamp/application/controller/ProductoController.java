package com.codigo.proyecto_bootcamp.application.controller;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ProductoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.ProductoServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto_bootcamp/v1/producto")
@AllArgsConstructor


public class ProductoController {

    private final ProductoServiceIn productoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<ProductoDto> registrar(@RequestBody ProductoRequest requestProducto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.crearProductoIn(requestProducto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.buscarProductoporIdIn(id).get());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProductoDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.buscartodosProductosIn());

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(@PathVariable Long id, @RequestBody ProductoRequest productoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.actualizarProductoIn(id,productoRequest));

    }

    @DeleteMapping("/{id}")
    @GetMapping("{/id}")
    public ResponseEntity<ProductoDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoServiceIn.borrarProductoIn(id));

    }



}
