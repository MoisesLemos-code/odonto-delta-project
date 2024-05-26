package br.com.molens.odontoDelta.domain.usecase.cobranca.removerCobrancaPorId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoverCobrancaPorIdInput {

    @NotNull(message = "O atributo 'cobrancaId' não pode ser nulo.")
    private Long cobrancaId;

}
