package com.nelcfood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;
    private String tipo;
    private String titulo;
    private String detalhes;

    private String mensagemUsuario;
    private LocalDateTime timestamp;
}
