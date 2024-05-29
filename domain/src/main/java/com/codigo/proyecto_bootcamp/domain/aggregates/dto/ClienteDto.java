package com.codigo.proyecto_bootcamp.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ClienteDto {

    private Long clientId;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String tipoDoc;
    private String numeroDoc;
    private String email;
    private String estado;
    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
    private String userDelete;
    private Timestamp dateDelete;

}
