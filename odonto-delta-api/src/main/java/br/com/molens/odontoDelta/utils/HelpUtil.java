package br.com.molens.odontoDelta.utils;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

public class HelpUtil {
    public static String retiraAcentuacao(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String obterApenasNumeros(String texto){

        if(Objects.isNull(texto)){
            return null;
        }
        return texto.replaceAll("[^0-9]", "");
    }

    public static int obterIdade(Date dataNascimento){
        return obterIdade(LocalDateTime.ofInstant(dataNascimento.toInstant(), ZoneOffset.UTC).toLocalDate());
    }

    public static int obterIdade(final LocalDate aniversario) {
        return Period.between(aniversario, LocalDate.now()).getYears();
    }
}