package com.codigo.proyecto_bootcamp.domain.ports.in;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ClienteDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ClienteRequest;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceIn {

    ClienteDto crearClienteIn(ClienteRequest clienteRequest);
    Optional<ClienteDto> buscarClienteporIdIn(Long id);
    List<ClienteDto> buscartodosClientesIn();
    ClienteDto actualizarClienteIn(Long id, ClienteRequest clienteRequest);
    ClienteDto borrarClienteIn(Long id);


}
