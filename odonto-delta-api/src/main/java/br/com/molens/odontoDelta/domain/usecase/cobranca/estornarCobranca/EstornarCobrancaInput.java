package br.com.molens.odontoDelta.domain.usecase.cobranca.estornarCobranca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstornarCobrancaInput {

    @NotNull(message = "O atributo 'id' não pode ser nulo.")
    private Long id;
    private Long empresaId;
}
