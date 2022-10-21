package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

import java.util.HashMap;
import java.util.Map;

public class BuscaAvancada implements Busca {

    private Map<String, String> metadados;

    /**
     * @param metadados
     */
    public BuscaAvancada(Map<String, String> metadados) {
        this.metadados = metadados;
    }

    /**
     * Realiza a busca avançada a partir da consulta ao DocumentoService.
     * 
     * O DocumentoService realiza apenas operações simples de busca, mas sem
     * ordenação ou tratamento da lógica de relevância.
     *
     * @param ds DocumentoService a ser utilizado para busca.
     * @return Mapa com os documentos encontrados e o fator de relevância de cada
     *         operação (na busca avançada, todos os elementos são igualmente
     *         relevantes).
     */
    public Map<Documento, Integer> busca(DocumentoService ds) {
        Map<Documento, Integer> respostaDocumento = new HashMap<>();
        for (Documento d : ds.busca(this.metadados)) {
            respostaDocumento.put(d, respostaDocumento.getOrDefault(d, 0));
        }
        return respostaDocumento;
    }

    /**
     * Descreve uma consulta para explicar a consulta que foi feita.
     *
     * @return Descrição da busca, onde cada linha representa um parâmetro de busca
     *         e as colunas representam um detelhamento de cada parâmetro.
     */
    public String[][] descreveConsulta() {
        String[][] resultado = new String[this.metadados.size()][];
        for (String key : this.metadados.keySet()) {
            int i = 0;
            resultado[i] = new String[] { "METADADO " + (i + 1) + this.metadados.get(key) };
            i++;
        }
        return resultado;
    }
}
