package com.codigo.proyecto_bootcamp.infraestructure.adapters;

import com.codigo.proyecto_bootcamp.domain.aggregates.constants.Constant;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ClienteDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.dto.ReniecDto;
import com.codigo.proyecto_bootcamp.domain.aggregates.request.ClienteRequest;
import com.codigo.proyecto_bootcamp.domain.ports.out.ClienteServiceOut;
import com.codigo.proyecto_bootcamp.infraestructure.client.ClientReniec;
import com.codigo.proyecto_bootcamp.infraestructure.dao.ClienteRepository;
import com.codigo.proyecto_bootcamp.infraestructure.entity.ClienteEntity;
import com.codigo.proyecto_bootcamp.infraestructure.mapper.ClienteMapper;
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

public class ClienteAdapter implements ClienteServiceOut {
    private final ClienteRepository clienteRepository;
    private final ClientReniec clientReniec;
    private final RedisService redisService;

    @Value("${token.resu}")
    private String tokenReniec;

    @Override
    public ClienteDto crearClienteOut(ClienteRequest clienteRequest) {
        ClienteEntity clienteEntity = getEntity(clienteRequest, false, null);
        return ClienteMapper.fromEntity(clienteRepository.save(clienteEntity));
    }

    private ClienteEntity getEntity(ClienteRequest clienteRequest, boolean actualiza, Long id){
        // Exec servicio
        ReniecDto reniecDto = getExecReniec(clienteRequest.getNumDoc());
        ClienteEntity entity = new ClienteEntity();
        entity.setTipoDoc(reniecDto.getTipoDocumento());
        entity.setNumeroDoc(reniecDto.getNumeroDocumento());
        entity.setNombres(reniecDto.getNombres());
        entity.setApellidos(reniecDto.getApellidoPaterno() + " "+ reniecDto.getApellidoMaterno());
        entity.setEstado(Constant.STATUS_ACTIVE);
        entity.setTelefono(clienteRequest.getTelefono());
        entity.setEmail(clienteRequest.getEmail());
        if(actualiza){
            // si actualizo hago esto
            entity.setClienteId(id);
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
    public Optional<ClienteDto> buscarClienteporIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id);
        if(redisInfo != null){
            ClienteDto clienteDto = Util.convertirDesdeString(redisInfo, ClienteDto.class);
            return Optional.of(clienteDto);
        }else{
            ClienteDto clienteDto = ClienteMapper.fromEntity(clienteRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(clienteDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERCLIENTE+id, dataForRedis, 2);

            return Optional.of(clienteDto);
        }
    }

    @Override
    public List<ClienteDto> buscartodosClientesOut() {
        List<ClienteDto> listaDto = new ArrayList<>();
        List<ClienteEntity> entidades = clienteRepository.findAll();
        for(ClienteEntity dato: entidades){
            listaDto.add(ClienteMapper.fromEntity(dato));
        }
        return listaDto;
    }



    @Override
    public ClienteDto actualizarClienteOut(Long id, ClienteRequest clienteRequest) {
        Optional<ClienteEntity> datoExtraido = clienteRepository.findById(id);
        if(datoExtraido.isPresent()){
            ClienteEntity clienteEntity = getEntity(clienteRequest,true, id);
            return ClienteMapper.fromEntity(clienteRepository.save(clienteEntity));
        }else {
            throw new RuntimeException();

        }

    }

    @Override
    public ClienteDto borrarClienteOut(Long id) {
        Optional<ClienteEntity> datoExtraido = clienteRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setEstado(Constant.STATUS_INACTIVE);
            datoExtraido.get().setUserDelete(Constant.USUA_ADMIN);
            datoExtraido.get().setDateCreate(getTimestamp());
            return ClienteMapper.fromEntity(clienteRepository.save(datoExtraido.get()));
        }else {
            throw new RuntimeException();

        }
    }
}
