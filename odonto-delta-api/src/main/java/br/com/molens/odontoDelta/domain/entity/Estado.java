package br.com.molens.odontoDelta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    private Long id;
    private String sigla;
    private String nome;
}
