package com.codigo.proyecto_bootcamp.domain.impl;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.PedidoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.PedidoServiceIn;
import com.codigo.proyecto_bootcamp.domain.ports.out.PedidoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor

public class PedidoServiceImpl implements PedidoServiceIn {

    public final PedidoServiceOut pedidoServiceOut;
    @Override
    public PedidoDto crearPedidoIn(PedidoRequest pedidoRequest) {
        return pedidoServiceOut.crearPedidoOut(pedidoRequest);
    }

    @Override
    public Optional<PedidoDto> buscarPedidoporIdIn(Long id) {
        return pedidoServiceOut.buscarPedidoporIdOut(id);
    }

    @Override
    public List<PedidoDto> buscartodosPedidosIn() {
        return pedidoServiceOut.buscartodosPedidosOut();
    }

    @Override
    public PedidoDto actualizarPedidoIn(Long id, PedidoRequest pedidoRequest) {
        return pedidoServiceOut.actualizarPedidoOut(id, pedidoRequest);
    }

    @Override
    public PedidoDto borrarPedidoIn(Long id) {
        return pedidoServiceOut.borrarPedidoOut(id);
    }
}
