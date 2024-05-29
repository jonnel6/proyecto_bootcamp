package com.codigo.proyecto_bootcamp.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;
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
