package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

public class ApresentacaoCaixaAlta implements Apresentacao {

    /**
     * Documento a ser apresentado em caixa alta
     */
    private Documento doc;

    /**
     * Construtor que recebe o documento a ser apresentado.
     *
     * @param doc Documento a ser apresentado.
     */
    ApresentacaoCaixaAlta(Documento doc) {
        this.doc = doc;
    }

    /**
     * Apresenta o documento em caixa alta
     *
     * @return Um String contendo o texto original do documento, em caixa alta
     */
    public String apresenta() {
        return this.doc.getOriginal().toUpperCase();
    }
}
