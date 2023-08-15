package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaDeCidadesPorEstadoInput {

    @NotNull(message = "O atributo 'idEstado' não pode ser nulo.")
        private Long idEstado;
}
