package br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario;

import br.com.molens.odontoDelta.domain.exception.InserirUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.usuario.inserirUsuario.conveter.InserirUsuarioOutputConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class InserirUsuarioUsecase {

    private UsuarioDataProvider usuarioDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private MunicipioDataProvider municipioDataProvider;
    private PerfilDataProvider perfilDataProvider;
    private InserirUsuarioOutputConverter outputConverter;

    private BCryptPasswordEncoder passwordEncoder;

    public InserirUsuarioOutput executar(InserirUsuarioInput input){
        validarDadosEntrada(input);
        validarEmpresa(input);
        validarMunicipio(input);
        validarPerfil(input);
        validarUsuarioJaCadastrado(input);
        return inserirUsuario(input);
    }

    private void validarDadosEntrada(InserirUsuarioInput input) {

        if (Objects.isNull(input.getEmpresaId()) || input.getEmpresaId() == 0) {
            throw new InserirUsuarioException("Identificador de empresa inválido.");
        }
    }

    private void validarEmpresa(InserirUsuarioInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());
        if (!empresa.isPresent()) {
            throw new InserirUsuarioException("Empresa não identificada.");
        }
    }

    private void validarMunicipio(InserirUsuarioInput input) {
        Optional<Municipio> municipio = municipioDataProvider.buscarPorId(input.getEmpresaId());
        if (!municipio.isPresent()) {
            throw new InserirUsuarioException("Municipio não identificado.");
        }
    }

    private void validarPerfil(InserirUsuarioInput input) {
        Optional<Perfil> perfil = perfilDataProvider.buscarPorId(input.getPerfilId());
        if (!perfil.isPresent()) {
            throw new InserirUsuarioException("Perfil de usuário não identificado.");
        }
    }

    private void validarUsuarioJaCadastrado(InserirUsuarioInput input) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarUsuarioPorLogin(input.getLogin());
        if (usuario.isPresent()) {
            throw new InserirUsuarioException("Já existe um usuário com este login! (" + input.getLogin() + ")");
        }
    }

    private InserirUsuarioOutput inserirUsuario(InserirUsuarioInput input) {
        Usuario usuario = outputConverter.from(input);
        usuario.setSenha(passwordEncoder.encode(input.getSenha()));
        Long usuarioId = usuarioDataProvider.inserir(usuario);

        return new InserirUsuarioOutput(usuarioId);
    }
}