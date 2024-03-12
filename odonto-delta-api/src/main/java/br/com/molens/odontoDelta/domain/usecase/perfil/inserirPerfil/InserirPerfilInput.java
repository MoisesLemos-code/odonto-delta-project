package br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirPerfilInput {

    @NotNull(message = "O atributo 'nome' não pode ser nulo.")
    private String nome;
    @NotNull(message = "O atributo 'descricao' não pode ser nulo.")
    private String descricao;
    private Long empresaId;
    private Boolean ativo;
}
