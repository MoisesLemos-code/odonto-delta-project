package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPaginadaCobrancaInput {

    private int page;
    private int size;
    private String sort;
    private String direction;
    private Long pacienteId;
    private Long empresaId;
    private String conteudo;
    private String atributo;
    private String tipo;
    private String dataVencimento;
    private String statusEnum;
}
