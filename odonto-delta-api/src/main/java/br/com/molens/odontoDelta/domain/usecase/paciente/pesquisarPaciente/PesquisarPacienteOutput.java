package br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PesquisarPacienteOutput {

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
    }

}
