package br.com.molens.odontoDelta.domain.usecase.cobranca.inserirCobranca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirCobrancaOutput {

    private Long cobrancaId;

}
