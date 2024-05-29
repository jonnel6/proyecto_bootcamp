package com.codigo.proyecto_bootcamp.application.controller;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.PedidoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.PedidoServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto_bootcamp/v1/pedido")
@AllArgsConstructor


public class PedidoController {

    private final PedidoServiceIn pedidoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<PedidoDto> registrar(@RequestBody PedidoRequest requestPedido){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.crearPedidoIn(requestPedido));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.buscarPedidoporIdIn(id).get());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.buscartodosPedidosIn());

    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> actualizar(@PathVariable Long id, @RequestBody PedidoRequest pedidoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.actualizarPedidoIn(id,pedidoRequest));

    }

    @DeleteMapping("/{id}")
    @GetMapping("{/id}")
    public ResponseEntity<PedidoDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoServiceIn.borrarPedidoIn(id));

    }

}
