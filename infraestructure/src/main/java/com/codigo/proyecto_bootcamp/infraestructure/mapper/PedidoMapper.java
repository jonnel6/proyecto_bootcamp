package com.codigo.proyecto_bootcamp.infraestructure.mapper;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.infraestructure.entity.PedidoEntity;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDto fromEntity(PedidoEntity entity){
        PedidoDto dto = new PedidoDto();
        dto.setPedidoId(entity.getPedidoId());
        dto.setClienteId(entity.getCliente().getClienteId());
        dto.setEmpleadoId(entity.getEmpleado().getEmpleadoId());
        dto.setDetalles(entity.getDetalles() != null ?
                entity.getDetalles().stream().map(DetallePedidoMapper::fromEntity).collect(Collectors.toList()) : null);
        dto.setFechaPedido(entity.getFechaPedido());
        dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());
        dto.setUserCreate(entity.getUserCreate());
        dto.setDateCreate(entity.getDateCreate());
        dto.setUserModif(entity.getUserModif());
        dto.setDateModif(entity.getDateModif());
        dto.setUserDelete(entity.getUserDelete());
        dto.setDateDelete(entity.getDateDelete());
        return dto;
    }

}
