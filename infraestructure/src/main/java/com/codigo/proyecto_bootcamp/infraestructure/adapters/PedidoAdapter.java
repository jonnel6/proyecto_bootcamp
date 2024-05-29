package com.codigo.proyecto_bootcamp.infraestructure.adapters;

import com.codigo.proyecto_bootcamp.domain.aggregates.constants.Constant;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.PedidoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ProductoDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.DetallePedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.PedidoRequest;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ProductoRequest;
import com.codigo.proyecto_bootcamp.domain.ports.out.PedidoServiceOut;
import com.codigo.proyecto_bootcamp.infraestructure.dao.*;
import com.codigo.proyecto_bootcamp.infraestructure.entity.*;
import com.codigo.proyecto_bootcamp.infraestructure.mapper.ClienteMapper;
import com.codigo.proyecto_bootcamp.infraestructure.mapper.PedidoMapper;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PedidoAdapter implements PedidoServiceOut {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RedisService redisService;


    @Override
    public PedidoDto crearPedidoOut(PedidoRequest pedidoRequest) {
        PedidoEntity pedidoEntity = getEntity(pedidoRequest, false, null);
        return PedidoMapper.fromEntity(pedidoRepository.save(pedidoEntity));
    }

    private PedidoEntity getEntity(PedidoRequest pedidoRequest, boolean actualiza, Long id){
        PedidoEntity entity = new PedidoEntity();
        ClienteEntity cliente = clienteRepository.findById(pedidoRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        entity.setCliente(cliente);

        EmpleadoEntity empleado = empleadoRepository.findById(pedidoRequest.getEmpleadoId())
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        entity.setEmpleado(empleado);

        entity.setFechaPedido(getTimestamp());

        // Manejo de los detalles del pedido y cálculo del total
        final double[] total = {0.0};

        List<DetallePedidoEntity> nuevosDetalles = pedidoRequest.getDetalles().stream().map(detallePedidoRequest -> {
            DetallePedidoEntity detalle;
            if (detallePedidoRequest.getDetalleId() != null) {
                // Si se está actualizando un detalle existente
                detalle = detallePedidoRepository.findById(detallePedidoRequest.getDetalleId())
                        .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
                detalle.setDetalleId(detallePedidoRequest.getDetalleId());
                detalle.setUserModif(Constant.USUA_ADMIN);
                detalle.setDateModif(new Timestamp(System.currentTimeMillis()));


            } else {
                // Si se está creando un nuevo detalle
                detalle = new DetallePedidoEntity();
                detalle.setPedido(entity);
            }

            ProductoEntity producto = productoRepository.findById(detallePedidoRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            detalle.setProducto(producto);
            detalle.setCantidad(detallePedidoRequest.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());  // Establece el precio unitario del producto


            // Calcula el subtotal y lo suma al total
            double subtotal = detalle.getCantidad() * producto.getPrecio();
            detalle.setSubtotal(subtotal);
            total[0] += subtotal;
            detalle.setEstado(Constant.STATUS_ACTIVE);
            detalle.setUserCreate(Constant.USUA_ADMIN);
            detalle.setDateCreate(new Timestamp(System.currentTimeMillis()));

            return detalle;
        }).collect(Collectors.toList());

        // Eliminar los detalles que ya no están en la lista de nuevosDetalles
        if (actualiza) {
            entity.setPedidoId(id);
            entity.setDetalles(nuevosDetalles);
            List<DetallePedidoEntity> detallesExistentes = entity.getDetalles();
            for (DetallePedidoEntity detalleExistente : detallesExistentes) {
                if (nuevosDetalles.stream().noneMatch(d -> d.getDetalleId().equals(detalleExistente.getDetalleId()))) {
                   detalleExistente.setEstado(Constant.STATUS_INACTIVE);
                }
            }
        }

        entity.setDetalles(nuevosDetalles);
        entity.setTotal(total[0]);
        entity.setEstado(Constant.STATUS_ACTIVE);

        if (actualiza) {
            // Si se está actualizando, configurar campos de modificación
            entity.setPedidoId(id);


            PedidoEntity pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            entity.setUserCreate(pedido.getUserCreate());
            entity.setDateCreate(pedido.getDateCreate());
            entity.setUserModif(Constant.USUA_ADMIN);
            entity.setDateModif(new Timestamp(System.currentTimeMillis()));
        } else {
            // Si se está creando, configurar campos de creación
            entity.setUserCreate(Constant.USUA_ADMIN);
            entity.setDateCreate(new Timestamp(System.currentTimeMillis()));
        }

        return entity;

        }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);}

    @Override
    public Optional<PedidoDto> buscarPedidoporIdOut(Long id) {
            String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPEDIDO+id);
            if(redisInfo != null){
                PedidoDto pedidoDto = Util.convertirDesdeString(redisInfo, PedidoDto.class);
                return Optional.of(pedidoDto);
            }else{
                PedidoDto pedidoDto = PedidoMapper.fromEntity(pedidoRepository.findById(id).get());
                String dataForRedis = Util.convertirAStringD(pedidoDto);
                redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPEDIDO+id, dataForRedis, 2);

                return Optional.of(pedidoDto);
            }
    }

    @Override
    public List<PedidoDto> buscartodosPedidosOut() {
        List<PedidoDto> listaDto = new ArrayList<>();
        List<PedidoEntity> entidades = pedidoRepository.findAll();
        for(PedidoEntity dato: entidades){
            listaDto.add(PedidoMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public PedidoDto actualizarPedidoOut(Long id, PedidoRequest pedidoRequest) {
        Optional<PedidoEntity> datoExtraido = pedidoRepository.findById(id);
        if(datoExtraido.isPresent()){
            PedidoEntity pedidoEntity = getEntity(pedidoRequest,true, id);
            return PedidoMapper.fromEntity(pedidoRepository.save(pedidoEntity));
        }else {
            throw new RuntimeException();

        }
    }

    @Override
    public PedidoDto borrarPedidoOut(Long id) {
        Optional<PedidoEntity> datoExtraido = pedidoRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(Constant.STATUS_INACTIVE);
            datoExtraido.get().setUserDelete(Constant.USUA_ADMIN);
            datoExtraido.get().setDateCreate(getTimestamp());
            return PedidoMapper.fromEntity(pedidoRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();

        }
    }
}
