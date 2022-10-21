package com.matheusgr.lunr.documento;

import java.util.Map;

/**
 * Documento representa um conjunto textual que será indexado pelo Lunr.
 */
public interface Documento {

    /**
     * Retorna a quantidade de texto útil, sendo definido como a quantidade de
     * caracteres úteis (sem caracteres estranhos e sem espaços) sobre o total de
     * caracteres original (incluindo espaços).
     *
     * @return Percentual de texto útil (entre 0 e 1, inclusives).
     */
    double metricaTextoUtil();

    /**
     * Retorna a identificação do documento. A documentação é definida pelo próprio
     * documento e é uma String sem formatação específica. Todo documento gerado
     * pelo Lunr começa com o símbolo "_". O identificador não pode ser vazio ou
     * nulo.
     *
     * @return Identificação do documento.
     * @throws NullPointerException Caso o ID seja nulo.
     */
    String getId();

    /**
     * Retorna os termos do texto em formato de String. Não podem existir termos
     * vazios (só de espaços, tabulações ou string vazia).
     *
     * @return Array de termos extraídos do documento.
     */
    String[] getTexto();

    /**
     * Retorna metadados referente ao documento. Detalhes como seu tipo, autor, ou
     * características próprias do documento.
     * <p>
     * Idealmente, todo metadado terá como características:
     * <p>
     * LINHAS, TAMANHO (número de caracteres), METADATADATE (hora atual do sistema
     * na criação dos metadados, em ms), TIPO.
     *
     * @return Mapa com os metadados descrito em forma textual.
     */
    Map<String, String> getMetadados();

    /**
     * Retorna o documento original, sem nenhuma transformação sofrida.
     *
     * @return o documento original usado na criação da entidade Documento
     */
    String getOriginal();

    /**
     * Retorna a quandidade de primeiras linhas a serem exibidas durante
     * o preview (apresentação) do documento no modo N_PRIMEIRAS
     *
     * @return Um inteiro representando a quantidade de linhas a serem exibidas na apresentação
     */
    int getQuantidadePrimeirasLinhasApresentar();

    /**
     * Retorna a quandidade de ultimas linhas a serem exibidas durante
     * o preview (apresentação) do documento no modo N_ULTIMAS
     *
     * @return Um inteiro representando a quantidade de linhas a serem exibidas na apresentação
     */
    int getQuantidadeUltimasLinhasApresentar();

}
