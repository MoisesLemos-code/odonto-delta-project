package br.com.molens.odontoDelta.domain.usecase.paciente.buscaPaginada;

import br.com.molens.odontoDelta.gateway.entity.Municipio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaPaginadaPacienteOutput {


    private List<Item> items;
    private Long totalPages;
    private Long totalElements;

    @Data
    @NoArgsConstructor
    public static class Item {
        private Long id;
        private String nome;
        private String email;
        private String cnpjCpf;
        private String telefone;
        private Municipio municipio;
    }

}
