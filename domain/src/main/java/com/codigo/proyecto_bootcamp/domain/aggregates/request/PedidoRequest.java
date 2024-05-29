package com.codigo.proyecto_bootcamp.domain.aggregates.request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter

public class PedidoRequest {
    private Long pedidoId;
    private Long clientId;
    private Long empleadoId;
    private double total;
    private String observaciones;
    private List<DetallePedidoRequest> detalles;

}
