package br.com.molens.odontoDelta.application.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserBody {

    private Long userId;
    private String login;
    private String nomeCompleto;
    private String token;
    private boolean isAdmin;

}
