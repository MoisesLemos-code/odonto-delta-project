package br.com.molens.odontoDelta.domain.usecase.perfil.atualizarPerfil;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPerfilInput {

    private Long id;
    @NotNull(message = "O atributo 'nome' não pode ser nulo.")
    private String nome;
    @NotNull(message = "O atributo 'descricao' não pode ser nulo.")
    private String descricao;
    private Long empresaId;
    private Boolean ativo;
}
