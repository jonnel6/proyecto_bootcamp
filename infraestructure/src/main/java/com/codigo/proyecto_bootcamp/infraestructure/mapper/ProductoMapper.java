package com.codigo.proyecto_bootcamp.infraestructure.mapper;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.infraestructure.entity.ProductoEntity;

public class ProductoMapper {

    public static ProductoDto fromEntity(ProductoEntity entity){
        ProductoDto dto = new ProductoDto();
        dto.setProductId(entity.getProductId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
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
