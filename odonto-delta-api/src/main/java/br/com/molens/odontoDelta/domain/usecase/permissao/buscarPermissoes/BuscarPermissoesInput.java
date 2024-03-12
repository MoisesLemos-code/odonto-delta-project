package br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPermissoesInput {

    private Long empresaId;
}
