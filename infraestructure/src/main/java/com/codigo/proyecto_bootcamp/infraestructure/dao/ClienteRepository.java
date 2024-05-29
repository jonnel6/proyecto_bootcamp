package com.codigo.proyecto_bootcamp.infraestructure.dao;

import com.codigo.proyecto_bootcamp.infraestructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
