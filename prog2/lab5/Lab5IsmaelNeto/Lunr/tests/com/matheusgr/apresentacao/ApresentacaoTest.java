package com.matheusgr.apresentacao;

import com.matheusgr.lunr.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApresentacaoTest extends BaseTest {

    @Test
    void testApresentacaoAusente() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            this.apresentacaoController.apresenta("SAPATO", TipoApresentacao.CAIXA_ALTA);
        });
        assertEquals("ID fornecido não possui um documento associado.", e.getMessage());
    }

    @Test
    void testApresentacaoPrimeiras() {
        String apresentacao = this.apresentacaoController.apresenta(HTML_ID, TipoApresentacao.N_PRIMEIRAS);
        String esperado = "<!doctype html>\r\n" + "<html>\r\n" + "<head>\r\n" + "    <title>Example Domain</title>\r\n"
                + "\r\n";
        assertEquals(esperado, apresentacao);
    }

    @Test
    void testApresentacaoUltimas() {
        String apresentacao = this.apresentacaoController.apresenta(JAVA_ID, TipoApresentacao.N_ULTIMAS);
        String esperado = "\t\r\n" +
                "\tpublic void adicionaDocumentoTxt(String id, String use) {\r\n" +
                "\t\tthis.dc.adicionaDocumentoTxt(id, use);\r\n" +
                "\t}\r\n" +
                "}\n";
        assertEquals(esperado, apresentacao);
    }

    @Test
    void testApresentacaoCaixaAlta() {
        String apresentacao = this.apresentacaoController.apresenta(TEXTO3_ID, TipoApresentacao.CAIXA_ALTA);
        assertEquals("UM TEXTO MUITO LEGAL, APENAS !\r\n", apresentacao);
    }

}