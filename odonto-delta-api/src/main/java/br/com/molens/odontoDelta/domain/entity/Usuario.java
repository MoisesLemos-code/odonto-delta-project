package br.com.molens.odontoDelta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;
    private String login;
    private String nome;
    private String email;
    private String cnpjCpf;
    private String telefone;
    private Municipio municipio;
    private String cep;
    private String bairro;
    private String logradouro;
    private String logradouroNumero;
    private String complemento;
    private Perfil perfil;
    private Empresa empresa;
    private Boolean situacao;
    private String cargo;
    private String departamento;
    private String dataHoraUltimoAcesso;

}
