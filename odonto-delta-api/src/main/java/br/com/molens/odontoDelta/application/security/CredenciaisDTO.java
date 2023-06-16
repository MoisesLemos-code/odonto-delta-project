package br.com.molens.odontoDelta.application.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String senha;
}
