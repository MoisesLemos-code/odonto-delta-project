package br.com.molens.odontoDelta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private Long id;
    private String nome;
    private String email;
    private String cnpjCpf;
    private String telefone;
    private String rg;
    private Date dataNascimento;
    private Municipio municipio;
    private String cep;
    private String bairro;
    private String logradouro;
    private String logradouroNumero;
    private String complemento;
    private Empresa empresa;
    private String situacao;
    private LocalDateTime ultimaVisita;
}