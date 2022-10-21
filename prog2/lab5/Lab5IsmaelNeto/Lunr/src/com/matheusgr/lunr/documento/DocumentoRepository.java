package com.matheusgr.lunr.documento;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Repositório de documentos. O repositório pode ter opreações simples de busca,
 * mas a lógica de ranking, limitação e ordenação deve ficar em outra entidade.
 * 
 * O ID de um documento é único.
 */
class DocumentoRepository {

    /**
     * Mapa <id, documento> que guarda todos os documentos cadastrados no sistema.
     */
    private Map<String, Documento> documentos;
    private ValidadorDocumentos validador;

    /**
     * Construção padrão do repositório de documentos.
     */
    DocumentoRepository() {
        this.documentos = new HashMap<>();
        this.validador = new ValidadorDocumentos();
    }

    /**
     * Adiciona o documento. O documento é validado para garantir a consistência do
     * documento (sem termos e id vazios).
     *
     * @param d Documento a ser adicionado.
     */
    void adiciona(Documento d) {
        this.validador.validacao(d.getId(), d.getTexto());
        this.documentos.put(d.getId(), d);
    }

    /**
     * Recupera um documento do repositório.
     *
     * @param id ID do documento.
     * @return Documento, caso exista.
     */
    Optional<Documento> recupera(String id) {
        this.validador.validacao(id);
        Documento doc = this.documentos.get(id);

        return Optional.ofNullable(doc);
    }

    /**
     * Retorna o total de documentos cadastrados.
     *
     * @return O total de documentos cadastrados.
     */
    int totalDocumentos() {
        return this.documentos.size();
    }

    /**
     * Realiza uma busca pelos termos.
     *
     * @param termo Termo a ser buscado.
     * @return Conjunto de documentos com o termo.
     */
    public Set<Documento> busca(String termo) {
        Collection<Documento> docs = this.documentos.values();
        return docs.stream()
                .filter((x) -> Arrays.binarySearch(x.getTexto(), termo) > 0)
                .collect(Collectors.toSet());
    }

    /**
     * Realiza uma busca pelos metadados fornecidos.
     *
     * @param metadados Metadados a serem buscados.
     * @return Conjunto de documentos que possuem todos os metadados fornecidos.
     */
    public Set<Documento> busca(Map<String, String> metadados) {
        Collection<Documento> docs = this.documentos.values();
        Set<Documento> response = docs.stream()
                .filter((e) -> {
                    for (String key : metadados.keySet()) {
                        if (e.getMetadados().get(key).equals(metadados.get(key))) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                    return true;
                }).collect(Collectors.toSet());

        return response;
    }

}
