package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

import java.util.Map;

public interface Busca {

    Map<Documento, Integer> busca(DocumentoService ds);

    String[][] descreveConsulta();

}
