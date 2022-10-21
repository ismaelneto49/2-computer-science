package com.matheusgr.apresentacao;

public enum TipoApresentacao {
    N_PRIMEIRAS("N primeiras linhas"),
    N_ULTIMAS("N últimas linhas"),
    CAIXA_ALTA("Caixa alta");

    private String descricao;

    TipoApresentacao(String descricao) {
        this.descricao = descricao;
    }
}
