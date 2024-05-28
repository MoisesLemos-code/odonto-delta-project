package br.com.molens.odontoDelta.domain.usecase.cobranca.estornarCobranca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstornarCobrancaOutput {

    private Long cobrancaId;
}
