package com.codigo.proyecto_bootcamp.infraestructure.dao;

import com.codigo.proyecto_bootcamp.infraestructure.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
