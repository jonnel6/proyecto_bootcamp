package com.codigo.proyecto_bootcamp.infraestructure.mapper;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.DetallePedidoDto;
import com.codigo.proyecto_bootcamp.infraestructure.entity.DetallePedidoEntity;

public class DetallePedidoMapper {

    public static DetallePedidoDto fromEntity (DetallePedidoEntity entity){
        DetallePedidoDto dto = new DetallePedidoDto();
        dto.setDetalleId(entity.getDetalleId());
        dto.setPedidoId(entity.getPedido().getPedidoId());
        dto.setProductoId(entity.getProducto().getProductId());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getSubtotal());
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
