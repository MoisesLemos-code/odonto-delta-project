package br.com.molens.odontoDelta.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Email {
    private Long produtoId;
    private List<EmailComModelo> emailsAEnviar;

    @Data
    @Builder
    public static class EmailComModelo {
        private String email;
        private String chaveModelo;
        private Map<String, Object> variaveis;
    }
}
