package br.com.molens.odontoDelta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesConexao {

    private String usuarioId;
    private String sessaoId;
}
