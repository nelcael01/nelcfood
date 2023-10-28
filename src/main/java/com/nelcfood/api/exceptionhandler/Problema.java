package com.nelcfood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Campo> campos;

    @Getter
    @Builder
    public static class Campo {
        private String nome;
        private String mensagemUsuario;
    }
}
