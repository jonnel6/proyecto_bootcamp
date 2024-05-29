package com.codigo.proyecto_bootcamp.infraestructure.dao;

import com.codigo.proyecto_bootcamp.infraestructure.entity.DetallePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Long> {
}
