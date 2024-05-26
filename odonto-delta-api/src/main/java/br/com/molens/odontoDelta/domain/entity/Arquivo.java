package br.com.molens.odontoDelta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {
    private String nome;
    private String extensao;
    private String contentType;
    private byte[] content;
    private String uri;
    private String url;

    public static Arquivo from(String uri) {
        return Arquivo.builder().uri(uri).build();
    }
}