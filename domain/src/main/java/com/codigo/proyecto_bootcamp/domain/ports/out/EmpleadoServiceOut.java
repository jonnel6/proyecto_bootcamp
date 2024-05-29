package com.codigo.proyecto_bootcamp.domain.ports.out;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.EmpleadoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.EmpleadoRequest;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceOut {

    EmpleadoDto crearEmpleadoOut(EmpleadoRequest empleadoRequest);
    Optional<EmpleadoDto> buscarEmpleadoporIdOut(Long id);
    List<EmpleadoDto> buscartodosEmpleadosOut();
    EmpleadoDto actualizarEmpleadoOut(Long id, EmpleadoRequest empleadoRequest);
    EmpleadoDto borrarEmpleadoOut(Long id);
}
