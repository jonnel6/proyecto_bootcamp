package com.codigo.proyecto_bootcamp.domain.ports.out;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface PedidoServiceOut {

    PedidoDto crearPedidoOut(PedidoRequest pedidoRequest);
    Optional<PedidoDto> buscarPedidoporIdOut(Long id);
    List<PedidoDto> buscartodosPedidosOut();
    PedidoDto actualizarPedidoOut(Long id, PedidoRequest pedidoRequest);
    PedidoDto borrarPedidoOut(Long id);
}
