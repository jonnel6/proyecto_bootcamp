package com.codigo.proyecto_bootcamp.infraestructure.mapper;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.EmpleadoDto;
import com.codigo.proyecto_bootcamp.infraestructure.entity.EmpleadoEntity;

public class EmpleadoMapper {

    public static EmpleadoDto fromEntity(EmpleadoEntity entity){
        EmpleadoDto dto = new EmpleadoDto();
        dto.setEmpleadoId(entity.getEmpleadoId());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setTipoDoc(entity.getTipoDoc());
        dto.setNumeroDoc(entity.getNumeroDoc());
        dto.setTelefono(entity.getTelefono());
        dto.setPuesto(entity.getPuesto());
        dto.setSalario(entity.getSalario());
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
