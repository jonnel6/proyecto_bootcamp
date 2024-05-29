package com.codigo.proyecto_bootcamp.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "empleados")
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Long empleadoId;

    private String nombres;
    private String apellidos;
    private String tipoDoc;
    private String numeroDoc;
    private String telefono;
    private String puesto;
    private Double salario;
    private String estado;
    private String userCreate;
    private Timestamp dateCreate;
    private String userModif;
    private Timestamp dateModif;
    private String userDelete;
    private Timestamp dateDelete;

}
