package com.codigo.proyecto_bootcamp.application.controller;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ClienteDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ClienteRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.ClienteServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto_bootcamp/v1/cliente")
@AllArgsConstructor


public class ClienteController {
    private final ClienteServiceIn clienteServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<ClienteDto> registrar(@RequestBody ClienteRequest requestCliente){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.crearClienteIn(requestCliente));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.buscarClienteporIdIn(id).get());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.buscartodosClientesIn());

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizar(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.actualizarClienteIn(id,clienteRequest));

    }

    @DeleteMapping("/{id}")
    @GetMapping("{/id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.borrarClienteIn(id));

    }




}
