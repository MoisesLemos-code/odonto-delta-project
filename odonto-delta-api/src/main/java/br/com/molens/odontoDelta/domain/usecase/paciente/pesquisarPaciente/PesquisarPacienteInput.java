package br.com.molens.odontoDelta.domain.usecase.paciente.pesquisarPaciente;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PesquisarPacienteInput {

    private String conteudo;
    private Long empresaId;
}
