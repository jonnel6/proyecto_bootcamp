package com.codigo.proyecto_bootcamp.domain.ports.in;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceIn {

    PedidoDto crearPedidoIn(PedidoRequest pedidoRequest);
    Optional<PedidoDto> buscarPedidoporIdIn(Long id);
    List<PedidoDto> buscartodosPedidosIn();
    PedidoDto actualizarPedidoIn(Long id, PedidoRequest pedidoRequest);
    PedidoDto borrarPedidoIn(Long id);
}
