package br.com.molens.odontoDelta.domain.usecase.municipioEstado.buscarEstados;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarEstadosOutput {

    private List<Item> items;

    @Data
    @NoArgsConstructor
    public static class Item {
        private Long id;
        private String sigla;
        private String nome;
    }
}
