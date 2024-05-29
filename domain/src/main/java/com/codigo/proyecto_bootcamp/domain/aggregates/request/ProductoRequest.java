package com.codigo.proyecto_bootcamp.domain.aggregates.request;

import lombok.*;

@Setter
@Getter

public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
}
