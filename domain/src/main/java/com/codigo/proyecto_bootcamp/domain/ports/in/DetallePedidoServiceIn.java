package com.codigo.proyecto_bootcamp.domain.ports.in;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.DetallePedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.PedidoRequest;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoServiceIn {
    DetallePedidoDto crearDetallePedidoIn(DetallePedidoRequest detallePedidoRequest);
    Optional<DetallePedidoDto> buscarDetallePedidoporIdIn(Long id);
    List<DetallePedidoDto> buscartodosDetallePedidoIn();
    DetallePedidoDto actualizarDetallePedidoIn(Long id, DetallePedidoRequest detallePedidoRequest);
    DetallePedidoDto borrarDetallePedidoIn(Long id);
}
