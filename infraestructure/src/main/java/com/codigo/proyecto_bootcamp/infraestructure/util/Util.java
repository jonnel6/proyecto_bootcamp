package com.codigo.proyecto_bootcamp.infraestructure.util;

import com.codigo.proyecto_bootcamp.domain.aggregates.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    public static String convertirAString(ClienteDto clienteDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(clienteDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static String convertirAStringD(PedidoDto pedidoDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(pedidoDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertirAStringE(DetallePedidoDto detallePedidoDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(detallePedidoDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertirAStringF(ProductoDto productoDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(productoDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    public static String convertirAStringC(EmpleadoDto empleadoDto){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(empleadoDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T convertirDesdeString(String json, Class<T> tipoClase)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, tipoClase);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
