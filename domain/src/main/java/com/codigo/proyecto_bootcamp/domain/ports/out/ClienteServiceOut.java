package com.codigo.proyecto_bootcamp.domain.ports.out;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ClienteDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ClienteRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceOut {

    ClienteDto crearClienteOut(ClienteRequest clienteRequest);
    Optional<ClienteDto> buscarClienteporIdOut(Long id);
    List<ClienteDto> buscartodosClientesOut();
    ClienteDto actualizarClienteOut(Long id, ClienteRequest clienteRequest);
    ClienteDto borrarClienteOut(Long id);

}
