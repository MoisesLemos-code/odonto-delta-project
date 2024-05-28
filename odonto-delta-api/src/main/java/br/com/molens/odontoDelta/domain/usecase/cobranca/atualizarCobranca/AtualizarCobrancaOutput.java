package br.com.molens.odontoDelta.domain.usecase.cobranca.atualizarCobranca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarCobrancaOutput {

    private Long cobrancaId;

}
