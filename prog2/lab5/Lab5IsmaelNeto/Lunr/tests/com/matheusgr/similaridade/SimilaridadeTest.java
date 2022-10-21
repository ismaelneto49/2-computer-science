package com.matheusgr.similaridade;

import static org.junit.jupiter.api.Assertions.*;

import com.matheusgr.lunr.BaseTest;
import org.junit.jupiter.api.Test;

class SimilaridadeTest extends BaseTest {

    @Test
    void testSimilaridadeDocumento1Ausente() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            this.similaridadeController.similaridade("SAPATO", HTML_ID);
        });
        assertEquals("Documento com o ID: SAPATO não existe no sistema", e.getMessage());
    }

    @Test
    void testSimilaridadeDocumento2Ausente() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            this.similaridadeController.similaridade(TEXTO3_ID, "AMALERO");
        });
        assertEquals("Documento com o ID: AMALERO não existe no sistema", e.getMessage());
    }

    @Test
    void testSimilaridadeSimilares() {
        double similaridade = this.similaridadeController.similaridade(TEXTO3_ID, TEXTO1_ID);
        assertEquals(0.1818, similaridade, 0.1);
    }

    @Test
    void testSimilaridadeNaoSimilares() {
        double similaridade = this.similaridadeController.similaridade(TEXTO3_ID, HTML_ID);
        assertEquals(0.0, similaridade, 0);
    }

}