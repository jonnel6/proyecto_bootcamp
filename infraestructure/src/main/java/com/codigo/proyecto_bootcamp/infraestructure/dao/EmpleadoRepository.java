package com.codigo.proyecto_bootcamp.infraestructure.dao;

import com.codigo.proyecto_bootcamp.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository <EmpleadoEntity, Long> {
}
