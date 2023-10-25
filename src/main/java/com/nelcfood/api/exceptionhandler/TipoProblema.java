package com.nelcfood.api.exceptionhandler;

public enum TipoProblema {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");

    private String title;
    private String uri;

    TipoProblema(String path, String title) {
        this.uri = "https//nelcfood.com.br" + path;
        this.title = title;
    }
}
