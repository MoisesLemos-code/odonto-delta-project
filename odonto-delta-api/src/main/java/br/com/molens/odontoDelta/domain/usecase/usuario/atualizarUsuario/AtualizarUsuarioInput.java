package br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario;

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
public class AtualizarUsuarioInput {

    private Long id;
    @NotNull(message = "O atributo 'login' não pode ser nulo.")
    private String login;
    @NotNull(message = "O atributo 'nome' não pode ser nulo.")
    private String nome;
    @Email(message = "O parametro 'email' é inválido")
    private String email;
    @Pattern(regexp = "^\\d{11}$|^\\d{14}$", message = "O parametro 'cpfCnpj' é inválido")
    private String cnpjCpf;
    private String telefone;
    private Long municipioId;
    private String cep;
    private String bairro;
    private String logradouro;
    private String logradouroNumero;
    private String complemento;
    private Long perfilId;
    @NotNull(message = "O atributo 'empresaId' não pode ser nulo.")
    private Long empresaId;
    private Boolean situacao;
    private String cargo;
    private String departamento;
    @NotNull(message = "O atributo 'senha' não pode ser nulo.")
    private String senha;
    @NotNull(message = "O atributo 'senhaAtual' não pode ser nulo.")
    private String senhaAtual;
}
