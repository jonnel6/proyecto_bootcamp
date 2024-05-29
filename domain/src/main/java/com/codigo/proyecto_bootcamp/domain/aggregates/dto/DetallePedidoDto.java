package com.codigo.proyecto_bootcamp.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter

public class DetallePedidoDto {

    private Long detalleId;
    private Long pedidoId;
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    private String estado;
    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
    private String userDelete;
    private Timestamp dateDelete;

}
