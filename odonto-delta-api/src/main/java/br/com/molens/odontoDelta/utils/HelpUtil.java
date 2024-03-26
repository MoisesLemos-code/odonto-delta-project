package br.com.molens.odontoDelta.utils;

import java.text.Normalizer;

public class HelpUtil {
    public static String retiraAcentuacao(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String obterApenasNumeros(String texto){
        return texto.replaceAll("[^0-9]", "");
    }
}