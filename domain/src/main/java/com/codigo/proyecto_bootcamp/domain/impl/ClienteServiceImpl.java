package com.codigo.proyecto_bootcamp.domain.impl;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ClienteDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ClienteRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.ClienteServiceIn;
import com.codigo.proyecto_bootcamp.domain.ports.out.ClienteServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class ClienteServiceImpl implements ClienteServiceIn {

    public final ClienteServiceOut clienteServiceOut;

    @Override
    public ClienteDto crearClienteIn(ClienteRequest clienteRequest) {
        return clienteServiceOut.crearClienteOut(clienteRequest);
    }

    @Override
    public Optional<ClienteDto> buscarClienteporIdIn(Long id) {
        return clienteServiceOut.buscarClienteporIdOut(id);
    }

    @Override
    public List<ClienteDto> buscartodosClientesIn() {
        return clienteServiceOut.buscartodosClientesOut();
    }

    @Override
    public ClienteDto actualizarClienteIn(Long id, ClienteRequest clienteRequest) {
        return clienteServiceOut.actualizarClienteOut(id, clienteRequest);
    }

    @Override
    public ClienteDto borrarClienteIn(Long id) {
        return clienteServiceOut.borrarClienteOut(id);
    }
}
