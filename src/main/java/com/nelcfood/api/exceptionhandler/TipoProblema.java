package com.nelcfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoProblema {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String titulo;
    private String tipo;

    TipoProblema(String tipo, String titulo) {
        this.tipo = "https//nelcfood.com.br" + tipo;
        this.titulo = titulo;
    }
}
