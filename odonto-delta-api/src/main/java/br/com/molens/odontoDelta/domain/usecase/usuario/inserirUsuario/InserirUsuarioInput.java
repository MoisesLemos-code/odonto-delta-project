package br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirUsuarioInput {

    @NotNull(message = "O atributo 'login' não pode ser nulo.")
    private String login;
    @NotNull(message = "O atributo 'nome' não pode ser nulo.")
    private String nome;
    @Email(message = "O parametro 'email' é inválido")
    private String email;
    private String cnpjCpf;
    private String telefone;
    private Long municipioId;
    private String cep;
    private String bairro;
    private String logradouro;
    private String logradouroNumero;
    private String complemento;
    private Long empresaId;
    private Boolean situacao;
    private String cargo;
    private String departamento;
    @NotNull(message = "O atributo 'senha' não pode ser nulo.")
    private String senha;
}
