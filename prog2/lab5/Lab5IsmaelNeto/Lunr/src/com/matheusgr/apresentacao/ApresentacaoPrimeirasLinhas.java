package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

public class ApresentacaoPrimeirasLinhas implements Apresentacao {

    /**
     * Documento a ser apresentado por suas N primeiras linhas
     * A informação de quantas linhas devem ser apresentadas está no Documento
     */
    private Documento doc;

    /**
     * Construtor que recebe o documento a ser apresentado.
     *
     * @param doc Documento a ser apresentado.
     */
    ApresentacaoPrimeirasLinhas(Documento doc) {
        this.doc = doc;
    }

    /**
     * Apresenta as N primeiras linhas de um documento
     *
     * @return Um String contendo as N primeiras linhas do documento a ser apresentado
     */
    public String apresenta() {
        String apresentacao = "";
        String[] linhasOriginal = this.doc.getOriginal().split("\n");

        int qtdLinhasApresentar = this.doc.getQuantidadePrimeirasLinhasApresentar();
        if (qtdLinhasApresentar > linhasOriginal.length) {
            qtdLinhasApresentar = linhasOriginal.length;
        }
        for (int i = 0; i < qtdLinhasApresentar; i++) {
            apresentacao += linhasOriginal[i] + "\n";
        }
        return apresentacao;
    }

}