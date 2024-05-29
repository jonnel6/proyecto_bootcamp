package com.codigo.proyecto_bootcamp.infraestructure.dao;

import com.codigo.proyecto_bootcamp.infraestructure.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
