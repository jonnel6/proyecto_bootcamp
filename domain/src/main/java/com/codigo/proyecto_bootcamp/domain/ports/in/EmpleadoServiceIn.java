package com.codigo.proyecto_bootcamp.domain.ports.in;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.EmpleadoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.EmpleadoRequest;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceIn {

    EmpleadoDto crearEmpleadoIn(EmpleadoRequest empleadoRequest);
    Optional<EmpleadoDto> buscarEmpleadoporIdIn(Long id);
    List<EmpleadoDto> buscartodosEmpleadosIn();
    EmpleadoDto actualizarEmpleadoIn(Long id, EmpleadoRequest empleadoRequest);
    EmpleadoDto borrarEmpleadoIn(Long id);
}
