package br.com.molens.odontoDelta.domain.usecase.paciente.buscarPacientePorId;

import br.com.molens.odontoDelta.gateway.entity.Municipio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarPacientePorIdOutput {

    private Long id;
    private String nome;
    private String email;
    private String cnpjCpf;
    private String telefone;
    private String rg;
    private Date dataNascimento;
    private int idade;
    private String cidade;
    private String estado;
    private Long municipioId;
    private Long estadoId;
    private String cep;
    private String bairro;
    private String logradouro;
    private String logradouroNumero;
    private String complemento;
    private String situacao;
    private Boolean ortodontia;
    private LocalDateTime ultimaVisita;
}
