package com.codigo.proyecto_bootcamp.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter


public class EmpleadoDto {

    private Long empleadoId;
    private String nombres;
    private String apellidos;
    private String tipoDoc;
    private String numeroDoc;
    private String puesto;
    private Double salario;
    private String telefono;
    private String estado;
    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
    private String userDelete;
    private Timestamp dateDelete;

}
