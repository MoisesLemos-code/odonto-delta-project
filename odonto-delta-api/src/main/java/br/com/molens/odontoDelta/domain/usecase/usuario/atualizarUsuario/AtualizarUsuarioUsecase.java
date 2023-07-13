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
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class AtualizarUsuarioUsecase {

    private UsuarioDataProvider usuarioDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private MunicipioDataProvider municipioDataProvider;
    private PerfilDataProvider perfilDataProvider;
    private AtualizarUsuarioOutputConverter outputConverter;

    public AtualizarUsuarioOutput executar(AtualizarUsuarioInput input) {
        validarDadosEntrada(input);
        buscarPaciente(input);
        buscarEmpresa(input);
        buscarMunicipio(input);
        buscarPerfil(input);
        validarUsuarioLoginJaCadastrado(input);
        Usuario usuario = atualizarUsuario(outputConverter.from(input));
        return outputConverter.to(usuario);
    }

    private void buscarPerfil(AtualizarUsuarioInput input) {
    }

    private void validarDadosEntrada(AtualizarUsuarioInput input) {

        if (Objects.isNull(input.getId())) {
            throw new AtualizarPacienteException("Identificador de usuário é obrigatório.");
        }
        if (input.getEmpresaId() == 0) {
            throw new AtualizarPacienteException("Identificador de empresa inválido.");
        }
    }

    private void buscarEmpresa(AtualizarUsuarioInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());

        if (!empresa.isPresent()) {
            throw new AtualizarUsuarioException("Empresa não encontrada.");
        }
    }

    private void buscarPaciente(AtualizarUsuarioInput input) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarPorId(input.getId());

        if (!usuario.isPresent()) {
            throw new AtualizarUsuarioException("Usuário não encontrado.");
        }
    }

    private void buscarMunicipio(AtualizarUsuarioInput input) {
        Optional<Municipio> municipio = municipioDataProvider.buscarPorId(input.getMunicipioId());

        if (!municipio.isPresent()) {
            throw new AtualizarUsuarioException("Municipio não encontrado.");
        }
    }
    
    private void validarUsuarioLoginJaCadastrado(AtualizarUsuarioInput input) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarUsuarioPorLogin(input.getLogin());
        if (usuario.isPresent()) {
            if (usuario.get().getId() != input.getId()) {
                throw new AtualizarUsuarioException("Já existe um usuário com este login! (" + input.getLogin() + ")");
            }
        }
    }

    private Usuario atualizarUsuario(Usuario usuario) {
        return usuarioDataProvider.atualizar(usuario);
    }

}
