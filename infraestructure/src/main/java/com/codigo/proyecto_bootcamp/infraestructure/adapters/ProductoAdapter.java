package com.codigo.proyecto_bootcamp.infraestructure.adapters;
import com.codigo.proyecto_bootcamp.domain.aggregates.constants.Constant;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ProductoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.out.ProductoServiceOut;
import com.codigo.proyecto_bootcamp.infraestructure.dao.ProductoRepository;
import com.codigo.proyecto_bootcamp.infraestructure.entity.ClienteEntity;
import com.codigo.proyecto_bootcamp.infraestructure.entity.ProductoEntity;
import com.codigo.proyecto_bootcamp.infraestructure.mapper.ClienteMapper;
import com.codigo.proyecto_bootcamp.infraestructure.mapper.ProductoMapper;
import com.codigo.proyecto_bootcamp.infraestructure.redis.RedisService;
import com.codigo.proyecto_bootcamp.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductoAdapter implements ProductoServiceOut {

    private final ProductoRepository productoRepository;
    private final RedisService redisService;

    @Override
    public ProductoDto crearProductoOut(ProductoRequest productoRequest) {
        ProductoEntity productoEntity = getEntity(productoRequest, false, null);
        return ProductoMapper.fromEntity(productoRepository.save(productoEntity));
    }

    private ProductoEntity getEntity(ProductoRequest productoRequest, boolean actualiza, Long id){
        // Exec servicio
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(productoRequest.getNombre());
        entity.setDescripcion(productoRequest.getDescripcion());
        entity.setPrecio(productoRequest.getPrecio());
        entity.setEstado(Constant.STATUS_ACTIVE);
        if(actualiza){
            // si actualizo hago esto
            entity.setProductId(id);
            entity.setUserModif(Constant.USUA_ADMIN);
            entity.setDateModif(getTimestamp());
        }else{
            // si no actualizo hago esto
            // auditoria

            entity.setUserCreate(Constant.USUA_ADMIN);
            entity.setDateCreate(getTimestamp());

        }


        return entity;


    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }


    @Override
    public Optional<ProductoDto> buscarProductoporIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id);
        if(redisInfo != null){
            ProductoDto productoDto = Util.convertirDesdeString(redisInfo, ProductoDto.class);
            return Optional.of(productoDto);
        }else{
            ProductoDto productoDto = ProductoMapper.fromEntity(productoRepository.findById(id).get());
            String dataForRedis = Util.convertirAStringF(productoDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id, dataForRedis, 2);

            return Optional.of(productoDto);
        }
    }

    @Override
    public List<ProductoDto> buscartodosProductosOut() {
        List<ProductoDto> listaDto = new ArrayList<>();
        List<ProductoEntity> entidades = productoRepository.findAll();
        for(ProductoEntity dato: entidades){
            listaDto.add(ProductoMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public ProductoDto actualizarProductoOut(Long id, ProductoRequest productoRequest) {
        Optional<ProductoEntity> datoExtraido = productoRepository.findById(id);
        if(datoExtraido.isPresent()){
            ProductoEntity productoEntity = getEntity(productoRequest,true, id);
            return ProductoMapper.fromEntity(productoRepository.save(productoEntity));
        }else {
            throw new RuntimeException();

        }
    }

    @Override
    public ProductoDto borrarProductoOut(Long id) {
        Optional<ProductoEntity> datoExtraido = productoRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(Constant.STATUS_INACTIVE);
            datoExtraido.get().setUserDelete(Constant.USUA_ADMIN);
            datoExtraido.get().setDateCreate(getTimestamp());
            return ProductoMapper.fromEntity(productoRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();

        }
    }
}
