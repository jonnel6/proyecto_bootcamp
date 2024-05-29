package com.codigo.proyecto_bootcamp.application.controller;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.EmpleadoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.EmpleadoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.EmpleadoServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto_bootcamp/v1/empleado")
@AllArgsConstructor

public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<EmpleadoDto> registrar(@RequestBody EmpleadoRequest requestEmpleado){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.crearEmpleadoIn(requestEmpleado));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> buscarxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.buscarEmpleadoporIdIn(id).get());

    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpleadoDto>> buscartodos(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.buscartodosEmpleadosIn());

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> actualizar(@PathVariable Long id, @RequestBody EmpleadoRequest empleadoRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.actualizarEmpleadoIn(id,empleadoRequest));

    }

    @DeleteMapping("/{id}")
    @GetMapping("{/id}")
    public ResponseEntity<EmpleadoDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.borrarEmpleadoIn(id));

    }


}
