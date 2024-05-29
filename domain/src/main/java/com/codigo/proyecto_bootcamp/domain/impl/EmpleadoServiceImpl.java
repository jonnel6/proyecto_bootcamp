package com.codigo.proyecto_bootcamp.domain.impl;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.EmpleadoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.EmpleadoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.EmpleadoServiceIn;
import com.codigo.proyecto_bootcamp.domain.ports.out.EmpleadoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class EmpleadoServiceImpl implements EmpleadoServiceIn {

    public final EmpleadoServiceOut empleadoServiceOut;
    @Override
    public EmpleadoDto crearEmpleadoIn(EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.crearEmpleadoOut(empleadoRequest);
    }

    @Override
    public Optional<EmpleadoDto> buscarEmpleadoporIdIn(Long id) {
        return empleadoServiceOut.buscarEmpleadoporIdOut(id);
    }

    @Override
    public List<EmpleadoDto> buscartodosEmpleadosIn() {
        return empleadoServiceOut.buscartodosEmpleadosOut();
    }

    @Override
    public EmpleadoDto actualizarEmpleadoIn(Long id, EmpleadoRequest empleadoRequest) {
        return empleadoServiceOut.actualizarEmpleadoOut(id, empleadoRequest);
    }

    @Override
    public EmpleadoDto borrarEmpleadoIn(Long id) {
        return empleadoServiceOut.borrarEmpleadoOut(id);
    }
}
