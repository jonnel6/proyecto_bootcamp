package com.codigo.proyecto_bootcamp.domain.impl;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.DetallePedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.DetallePedidoServiceIn;
import com.codigo.proyecto_bootcamp.domain.ports.out.DetallePedidoServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class DetallePedidoImpl implements DetallePedidoServiceIn {

    public final DetallePedidoServiceOut detallePedidoServiceOut;
    @Override
    public DetallePedidoDto crearDetallePedidoIn(DetallePedidoRequest detallePedidoRequest) {
        return detallePedidoServiceOut.crearDetallePedidoOut(detallePedidoRequest);
    }

    @Override
    public Optional<DetallePedidoDto> buscarDetallePedidoporIdIn(Long id) {
        return detallePedidoServiceOut.buscarDetallePedidoporIdOut(id);
    }

    @Override
    public List<DetallePedidoDto> buscartodosDetallePedidoIn() {
        return detallePedidoServiceOut.buscartodosDetallePedidoOut();
    }

    @Override
    public DetallePedidoDto actualizarDetallePedidoIn(Long id, DetallePedidoRequest detallePedidoRequest) {
        return detallePedidoServiceOut.actualizarDetallePedidoOut(id, detallePedidoRequest);
    }

    @Override
    public DetallePedidoDto borrarDetallePedidoIn(Long id) {
        return detallePedidoServiceOut.borrarDetallePedidoOut(id);
    }
}
