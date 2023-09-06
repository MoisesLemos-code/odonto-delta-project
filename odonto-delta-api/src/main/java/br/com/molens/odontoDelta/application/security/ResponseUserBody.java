package br.com.molens.odontoDelta.application.security;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserBody {

    private Long id;
    private String login;
    private String nomeCompleto;
    private String token;
    private boolean isAdmin;
    private Empresa empresa;
    private List<String> permissao;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Empresa{
        private Long id;
        private String nome;
        private String razaoSocial;
    }
}
