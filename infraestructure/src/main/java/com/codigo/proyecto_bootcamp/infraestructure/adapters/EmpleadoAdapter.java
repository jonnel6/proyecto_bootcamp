package com.codigo.proyecto_bootcamp.infraestructure.adapters;

import com.codigo.proyecto_bootcamp.domain.aggregates.constants.Constant;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.EmpleadoDto;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ReniecDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.EmpleadoRequest;

import com.codigo.proyecto_bootcamp.domain.ports.out.EmpleadoServiceOut;
import com.codigo.proyecto_bootcamp.infraestructure.client.ClientReniec;
import com.codigo.proyecto_bootcamp.infraestructure.dao.EmpleadoRepository;

import com.codigo.proyecto_bootcamp.infraestructure.entity.EmpleadoEntity;

import com.codigo.proyecto_bootcamp.infraestructure.mapper.EmpleadoMapper;
import com.codigo.proyecto_bootcamp.infraestructure.redis.RedisService;
import com.codigo.proyecto_bootcamp.infraestructure.util.Util;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class EmpleadoAdapter implements EmpleadoServiceOut {
    
    private final EmpleadoRepository empleadoRepository;
    private final ClientReniec clientReniec;
    private final RedisService redisService;
    
    @Value("${token.resu}")
    private String tokenReniec;

    @Override
    public EmpleadoDto crearEmpleadoOut(EmpleadoRequest empleadoRequest) {
        EmpleadoEntity empleadoEntity = getEntity(empleadoRequest, false, null);
        return EmpleadoMapper.fromEntity(empleadoRepository.save(empleadoEntity));
    }

    private EmpleadoEntity getEntity(EmpleadoRequest empleadoRequest, boolean actualiza, Long id){
        // Exec servicio
        ReniecDto reniecDto = getExecReniec(empleadoRequest.getNumDoc());
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setTipoDoc(reniecDto.getTipoDocumento());
        entity.setNumeroDoc(reniecDto.getNumeroDocumento());
        entity.setNombres(reniecDto.getNombres());
        entity.setApellidos(reniecDto.getApellidoPaterno() + " "+ reniecDto.getApellidoMaterno());
        entity.setEstado(Constant.STATUS_ACTIVE);
        entity.setTelefono(empleadoRequest.getTelefono());
        entity.setPuesto(empleadoRequest.getPuesto());
        entity.setSalario(empleadoRequest.getSalario());

        if(actualiza){
            // si actualizo hago esto
            entity.setEmpleadoId(id);
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

    private ReniecDto getExecReniec(String numDoc){
        String authorization = "Bearer "+tokenReniec;
        return clientReniec.getInfoReniec(numDoc,authorization);
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
    
    

    @Override
    public Optional<EmpleadoDto> buscarEmpleadoporIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPLEADO+id);
        if(redisInfo != null){
            EmpleadoDto empleadoDto = Util.convertirDesdeString(redisInfo, EmpleadoDto.class);
            return Optional.of(empleadoDto);
        }else{
            EmpleadoDto empleadoDto = EmpleadoMapper.fromEntity(empleadoRepository.findById(id).get());
            String dataForRedis = Util.convertirAStringC(empleadoDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPLEADO+id, dataForRedis, 2);

            return Optional.of(empleadoDto);
        }
    }

    @Override
    public List<EmpleadoDto> buscartodosEmpleadosOut() {
        List<EmpleadoDto> listaDto = new ArrayList<>();
        List<EmpleadoEntity> entidades = empleadoRepository.findAll();
        for(EmpleadoEntity dato: entidades){
            listaDto.add(EmpleadoMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public EmpleadoDto actualizarEmpleadoOut(Long id, EmpleadoRequest empleadoRequest) {
        Optional<EmpleadoEntity> datoExtraido = empleadoRepository.findById(id);
        if(datoExtraido.isPresent()){
            EmpleadoEntity empleadoEntity = getEntity(empleadoRequest,true, id);
            return EmpleadoMapper.fromEntity(empleadoRepository.save(empleadoEntity));
        }else {
            throw new RuntimeException();

        }
    }

    @Override
    public EmpleadoDto borrarEmpleadoOut(Long id) {
        Optional<EmpleadoEntity> datoExtraido = empleadoRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(Constant.STATUS_INACTIVE);
            datoExtraido.get().setUserDelete(Constant.USUA_ADMIN);
            datoExtraido.get().setDateCreate(getTimestamp());
            return EmpleadoMapper.fromEntity(empleadoRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();

        }
    }
}
