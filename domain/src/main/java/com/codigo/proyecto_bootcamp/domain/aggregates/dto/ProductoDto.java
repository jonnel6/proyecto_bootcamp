package com.codigo.proyecto_bootcamp.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Setter
@Getter

public class ProductoDto {
    private Long productId;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String estado;
    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
    private String userDelete;
    private Timestamp dateDelete;

}
