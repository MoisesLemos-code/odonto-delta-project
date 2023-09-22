package br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPerfilOutput {

    private Long perfilId;
}
