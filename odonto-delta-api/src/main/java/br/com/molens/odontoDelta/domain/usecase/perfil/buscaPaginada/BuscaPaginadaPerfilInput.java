package br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPaginadaPerfilInput {

    private int page;
    private int size;
    private String sort;
    private String direction;
    private String conteudo;
    private Long empresaId;
}
