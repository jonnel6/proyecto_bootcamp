package com.codigo.proyecto_bootcamp.infraestructure.mapper;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ClienteDto;
import com.codigo.proyecto_bootcamp.infraestructure.entity.ClienteEntity;


public class ClienteMapper {

    public static ClienteDto fromEntity(ClienteEntity entity){
        ClienteDto dto = new ClienteDto();
        dto.setClientId(entity.getClienteId());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setTelefono(entity.getTelefono());
        dto.setTipoDoc(entity.getTipoDoc());
        dto.setNumeroDoc(entity.getNumeroDoc());
        dto.setEmail(entity.getEmail());
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
