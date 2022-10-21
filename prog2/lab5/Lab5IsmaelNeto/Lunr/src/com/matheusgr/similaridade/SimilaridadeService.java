package com.matheusgr.similaridade;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

/**
 * Componente para tratamento da lógica de negócio relativa a similaridade.
 */
public class SimilaridadeService {

    private DocumentoService documentoService;

    /**
     * Inicialização da lógica de serviço.
     *
     * @param documentoService DocumentoService a ser utilizado pelo
     *                         SimilaridadeService.
     */
    public SimilaridadeService(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    /**
     * Calcula e retorna a similaridade.
     * <p>
     * Para o cálculo da similaridade:
     * <ul>
     * <li>Pega o documento 1</li>
     * <li>Pega o documento 2</li>
     * <li>Pega os termos do documento 1 e coloca em um conjunto (Termos1)</li>
     * <li>Pega os termos do documento 2 e coloca em um conjunto (Termos2)</li>
     * <li>Calcula a interseção entre Termos1 e Termos2 (Inters)</li>
     * <li>Calcula a união entre Termos1 e Termos2 (Uniao)</li>
     * <li>A similaridade é o tamanho de Inters sobre o tamanho do conjunto
     * Uniao</li>
     * </ul>
     *
     * @param docId1 Documento 1.
     * @param docId2 Documento 2.
     * @return Valor de similaridade (entre 0 e 1, inclusives) representando a
     * semelhança entre os documentos.
     */
    public double similaridade(String docId1, String docId2) throws IllegalArgumentException {
        Optional<Documento> optDoc1 = this.documentoService.recuperaDocumento(docId1);
        Optional<Documento> optDoc2 = this.documentoService.recuperaDocumento(docId2);
        if (optDoc1.isEmpty()) {
            throw new IllegalArgumentException("Documento com o ID: " + docId1 + " não existe no sistema");
        }
        if (optDoc2.isEmpty()) {
            throw new IllegalArgumentException("Documento com o ID: " + docId2 + " não existe no sistema");
        }
        Documento doc1 = optDoc1.get();
        Documento doc2 = optDoc2.get();

        HashSet<String> termosDoc1 = new HashSet<>(Arrays.asList(doc1.getTexto()));
        HashSet<String> termosDoc2 = new HashSet<>(Arrays.asList(doc2.getTexto()));

        HashSet<String> intersecao = new HashSet<>(termosDoc1);
        intersecao.retainAll(termosDoc2);
        HashSet<String> uniao = new HashSet<>(termosDoc1);
        uniao.addAll(termosDoc2);

        return ((double) intersecao.size() / (double) uniao.size());
    }

}
