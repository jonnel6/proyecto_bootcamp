package com.codigo.proyecto_bootcamp.infraestructure.adapters;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.DetallePedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.in.DetallePedidoServiceIn;
import com.codigo.proyecto_bootcamp.domain.ports.out.DetallePedidoServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DetallePedidoAdapter implements DetallePedidoServiceOut {


    @Override
    public DetallePedidoDto crearDetallePedidoOut(DetallePedidoRequest detallePedidoRequest) {
        return null;
    }

    @Override
    public Optional<DetallePedidoDto> buscarDetallePedidoporIdOut(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DetallePedidoDto> buscartodosDetallePedidoOut() {
        return List.of();
    }

    @Override
    public DetallePedidoDto actualizarDetallePedidoOut(Long id, DetallePedidoRequest detallePedidoRequest) {
        return null;
    }

    @Override
    public DetallePedidoDto borrarDetallePedidoOut(Long id) {
        return null;
    }
}
