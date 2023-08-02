package br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario;

import br.com.molens.odontoDelta.domain.exception.AtualizarPacienteException;
import br.com.molens.odontoDelta.domain.exception.AtualizarUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.converter.AtualizarUsuarioOutputConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@AllArgsConstructor
public class AtualizarUsuarioUsecase {

    private UsuarioDataProvider usuarioDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private MunicipioDataProvider municipioDataProvider;
    private PerfilDataProvider perfilDataProvider;
    private AtualizarUsuarioOutputConverter outputConverter;

    private BCryptPasswordEncoder passwordEncoder;

    public AtualizarUsuarioOutput executar(AtualizarUsuarioInput input) {
        validarDadosEntrada(input);
        buscarPaciente(input);

        buscarEmpresa(input);
        buscarMunicipio(input);
        buscarPerfil(input);

        validarUsuarioLoginJaCadastrado(input);
        validarSenhaUsuario(input);
        Usuario usuario = atualizarUsuario(outputConverter.from(input));
        return outputConverter.to(usuario);
    }

    private Perfil buscarPerfil(AtualizarUsuarioInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorId(input.getEmpresaId());

        if (perfil.isPresent()) {
            return perfil.get();
        }
        return null;
    }

    private void validarDadosEntrada(AtualizarUsuarioInput input) {

        if (Objects.isNull(input.getId())) {
            throw new AtualizarPacienteException("Identificador de usuário é obrigatório.");
        }
        if (input.getEmpresaId() == 0) {
            throw new AtualizarPacienteException("Identificador de empresa inválido.");
        }
    }

    private Empresa buscarEmpresa(AtualizarUsuarioInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());

        if (!empresa.isPresent()) {
            throw new AtualizarUsuarioException("Empresa não encontrada.");
        }
        return empresa.get();
    }

    private void buscarPaciente(AtualizarUsuarioInput input) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarPorId(input.getId());

        if (!usuario.isPresent()) {
            throw new AtualizarUsuarioException("Usuário não encontrado.");
        }
    }

    private Municipio buscarMunicipio(AtualizarUsuarioInput input) {
        Optional<Municipio> municipio = municipioDataProvider.buscarPorId(input.getMunicipioId());

        if (!municipio.isPresent()) {
            throw new AtualizarUsuarioException("Municipio não encontrado.");
        }
        return municipio.get();
    }

    private void validarUsuarioLoginJaCadastrado(AtualizarUsuarioInput input) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarUsuarioPorLogin(input.getLogin());
        if (usuario.isPresent()) {
            if (usuario.get().getId() != input.getId()) {
                throw new AtualizarUsuarioException("Já existe um usuário com este login! (" + input.getLogin() + ")");
            }
            if(!passwordEncoder.matches(input.getSenhaAtual(), usuario.get().getSenha())){
                throw new AtualizarUsuarioException("A senha atual é inválida!");
            }
        } else {
            throw new AtualizarUsuarioException("Não foi possível identificar esse usuário! (" + input.getLogin() + ")");
        }
    }

    private void validarSenhaUsuario(AtualizarUsuarioInput input) {
        String[] senhasInvalidas = new String[]{"1234", "12345", "123456", "1234567", "12345678", "123456789", "1234567890"};
        List<String> listSenhasInvalidas = Arrays.asList(senhasInvalidas);
        if (input.getSenha().length() < 4) {
            throw new AtualizarUsuarioException("A senha precisa ter no mínimo 4 caracteres");
        }
        if (listSenhasInvalidas.contains(input.getSenha())) {
            throw new AtualizarUsuarioException("A senha é muito simples!");
        }
    }


    private Usuario atualizarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioDataProvider.atualizar(usuario);
    }

}
