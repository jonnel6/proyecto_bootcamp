package com.codigo.proyecto_bootcamp.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;


@Setter
@Getter

public class PedidoDto {

    private Long pedidoId;
    private Long clienteId;
    private Long empleadoId;
    private List<DetallePedidoDto> detalles;
    private Timestamp fechaPedido;
    private Double total;
    private String estado;
    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
    private String userDelete;
    private Timestamp dateDelete;


}
