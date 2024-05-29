package com.codigo.proyecto_bootcamp.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetallePedidoRequest {
    private Long detalleId;
    private Long productId;
    private int cantidad;
    private Double precioUnitario;

}
