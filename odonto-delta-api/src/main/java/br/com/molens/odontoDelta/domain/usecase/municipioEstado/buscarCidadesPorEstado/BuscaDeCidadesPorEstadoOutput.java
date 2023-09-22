package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarCidadesPorEstado;

import br.com.molens.odontoDelta.gateway.entity.Estado;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaDeCidadesPorEstadoOutput {

    private List<Item> items;

    @Data
    @NoArgsConstructor
    public static class Item {
        private Long id;
        private String nome;
        private Estado estado;
        private String codigoIbge;
    }
}
