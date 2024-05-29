package com.codigo.proyecto_bootcamp.domain.ports.out;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.DetallePedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoServiceOut {
    DetallePedidoDto crearDetallePedidoOut(DetallePedidoRequest detallePedidoRequest);
    Optional<DetallePedidoDto> buscarDetallePedidoporIdOut(Long id);
    List<DetallePedidoDto> buscartodosDetallePedidoOut();
    DetallePedidoDto actualizarDetallePedidoOut(Long id, DetallePedidoRequest detallePedidoRequest);
    DetallePedidoDto borrarDetallePedidoOut(Long id);
}
