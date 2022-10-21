package com.matheusgr.apresentacao;

import java.util.Optional;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa
 * a apresentação de documentos.
 */
public class ApresentacaoService {

    private DocumentoService documentoService;

    /**
     * Inicialização da lógica de serviço.
     *
     * @param documentoService DocumentoService a ser utilizado pelo
     *                         ApresentacaoService.
     */
    public ApresentacaoService(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    /**
     * Realiza a apresentação do documento indicado.
     *
     * @param docId                Documento a ser apresentado.
     * @param tipoApresentacao     modo de apresentacao a ser aplicado sobre o
     *                             documento.
     */
    public String apresenta(String docId, TipoApresentacao tipoApresentacao)
            throws IllegalArgumentException {
        Optional<Documento> optDoc = this.documentoService.recuperaDocumento(docId);
        if (optDoc.isEmpty()) {
            throw new IllegalArgumentException("ID fornecido não possui um documento associado.");
        }
        Documento doc = optDoc.get();
        Apresentacao apresentacao;
        switch (tipoApresentacao) {
            case N_PRIMEIRAS:
                apresentacao = new ApresentacaoPrimeirasLinhas(doc);
                break;
            case N_ULTIMAS:
                apresentacao = new ApresentacaoUltimasLinhas(doc);
                break;
            case CAIXA_ALTA:
                apresentacao = new ApresentacaoCaixaAlta(doc);
                break;
            default:
                throw new IllegalArgumentException("Forma de apresentação fornecida não existe.");
        }
        return apresentacao.apresenta();
    }

}
