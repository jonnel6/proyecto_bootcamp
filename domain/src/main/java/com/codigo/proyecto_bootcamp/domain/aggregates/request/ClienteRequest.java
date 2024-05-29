package com.codigo.proyecto_bootcamp.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteRequest
{
    private String tipoDoc;
    private String numDoc;
    private String telefono;
    private String email;

}
