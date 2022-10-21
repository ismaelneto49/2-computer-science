package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

public class ApresentacaoUltimasLinhas implements Apresentacao {

    /**
     * Documento a ser apresentado por suas N ultimas linhas
     * A informação de quantas linhas devem ser apresentadas está no Documento
     */
    private Documento doc;

    /**
     * Construtor que recebe o documento a ser apresentado.
     *
     * @param doc Documento a ser apresentado.
     */
    ApresentacaoUltimasLinhas(Documento doc) {
        this.doc = doc;
    }

    /**
     * Apresenta as N ultimas linhas de um documento
     *
     * @return Um String contendo as N ultimas linhas do documento a ser apresentado
     */
    public String apresenta() {
        String apresentacao = "";
        String[] linhasOriginal = this.doc.getOriginal().split("\n");

        int qtdLinhasApresentar = this.doc.getQuantidadePrimeirasLinhasApresentar();
        if (qtdLinhasApresentar > linhasOriginal.length) {
            qtdLinhasApresentar = linhasOriginal.length;
        }
        for (int i = linhasOriginal.length - qtdLinhasApresentar; i < linhasOriginal.length; i++) {
            apresentacao += linhasOriginal[i] + "\n";
        }
        return apresentacao;
    }

}