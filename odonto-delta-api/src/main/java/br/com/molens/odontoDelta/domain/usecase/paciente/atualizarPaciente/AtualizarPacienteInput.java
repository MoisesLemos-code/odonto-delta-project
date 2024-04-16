package br.com.molens.odontoDelta.domain.usecase.paciente.atualizarPaciente;

import br.com.molens.odontoDelta.domain.enumeration.SituacaoPacienteEnum;
import br.com.molens.odontoDelta.utils.validate.DateFormat;
import br.com.molens.odontoDelta.utils.validate.enumeration.EnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarPacienteInput {

    private Long pacienteId;
    @NotNull(message = "O atributo 'nome' não pode ser nulo.")
    private String nome;
    @Email(message = "O parametro 'email' é inválido")
    private String email;
    private String cnpjCpf;
    @NotNull(message = "O atributo 'telefone' não pode ser nulo.")
    private String telefone;
    private String rg;
    @DateFormat(message="O parametro 'dataNascimento' é inválido, o formato esperado yyyy-MM-dd")
    private String dataNascimento;
    private Long municipioId;
    private String cep;
    private String bairro;
    private String logradouro;
    private String logradouroNumero;
    private String complemento;
    private Long empresaId;
    @EnumType(enumClass = SituacaoPacienteEnum.class, message = "O atributo 'situação' é inválido.")
    private String situacao;
}
