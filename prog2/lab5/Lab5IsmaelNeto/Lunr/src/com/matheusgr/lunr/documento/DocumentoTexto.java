package com.matheusgr.lunr.documento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import biblitex.TransformaTexto;

/**
 * Documento de texto simples. Não precisa de tratamento complexos nem tem
 * metadados próprios.
 */
class DocumentoTexto implements Documento {

    private String id;
    private String original;
    private String limpo;
    private Map<String, String> metadados;
    private String[] split;
    private int quantidadePrimeirasLinhasApresentar;
    private int quantidadeUltimasLinhasApresentar;

    /**
     * Construtor padrão do documento de texto.
     *
     * @param id                                  ID do documento.
     * @param txt                                 Texto do documento.
     * @param quantidadePrimeirasLinhasApresentar quantidade de linhas usadas na
     *                                            apresentacao de N primeiras linhas
     * @param quantidadeUltimasLinhasApresentar   quantidade de linhas usadas na
     *                                            apresentacao de N ultimas linhas
     */
    public DocumentoTexto(String id, String txt, int quantidadePrimeirasLinhasApresentar,
            int quantidadeUltimasLinhasApresentar) {
        this.id = id;
        this.original = txt;
        this.limpo = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.clean, txt).strip();
        this.quantidadePrimeirasLinhasApresentar = quantidadePrimeirasLinhasApresentar;
        this.quantidadeUltimasLinhasApresentar = quantidadeUltimasLinhasApresentar;
    }

    @Override
    public double metricaTextoUtil() {
        long extractedSize = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanSpaces, this.limpo)
                .length();
        return (1.0 * extractedSize) / this.original.length();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String[] getTexto() {
        if (this.split == null) {
            this.split = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanLines, this.limpo)
                    .split(" ");
            Arrays.sort(this.split);
        }
        return this.split;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentoTexto other = (DocumentoTexto) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "===" + this.id + System.lineSeparator() + this.limpo;
    }

    @Override
    public Map<String, String> getMetadados() {
        if (this.metadados != null) {
            return this.metadados;
        }
        this.metadados = new HashMap<String, String>();
        this.metadados.put("LINHAS", "" + this.original.chars().filter((value) -> '\n' == value).count());
        this.metadados.put("TAMANHO", "" + this.limpo.length());
        this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
        this.metadados.put("TIPO", "" + "txt");
        return this.metadados;
    }

    /**
     * Acessa o atributo que representa o texto original do documento
     * 
     * @return O texto original do documento
     */
    @Override
    public String getOriginal() {
        return this.original;
    }

    /**
     * Acessa o atributo que representa a quantidade de linhas usadas no modo de
     * apresentação de N primeiras linhas
     * 
     * @return um inteiro que representa quantas linhas devem ser apresentadas
     */
    @Override
    public int getQuantidadePrimeirasLinhasApresentar() {
        return this.quantidadePrimeirasLinhasApresentar;
    }

    /**
     * Acessa o atributo que representa a quantidade de linhas usadas no modo de
     * apresentação de N ultimas linhas
     * 
     * @return um inteiro que representa quantas linhas devem ser apresentadas
     */
    @Override
    public int getQuantidadeUltimasLinhasApresentar() {
        return this.quantidadeUltimasLinhasApresentar;
    }
}
