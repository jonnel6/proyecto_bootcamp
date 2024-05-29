package com.codigo.proyecto_bootcamp.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long pedidoId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private EmpleadoEntity empleado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedidoEntity> detalles;
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
