package br.com.molens.odontoDelta.domain.usecase.usuario.buscarUsuarioPorId;

import br.com.molens.odontoDelta.gateway.entity.Empresa;
import br.com.molens.odontoDelta.gateway.entity.Municipio;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarUsuarioPorIdOutput {

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
    private String senha;
}
