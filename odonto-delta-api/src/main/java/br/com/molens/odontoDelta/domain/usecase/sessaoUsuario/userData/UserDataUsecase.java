package br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData;

import br.com.molens.odontoDelta.application.security.ResponseUserBody;
import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.exception.SessaoUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioOutput;
import br.com.molens.odontoDelta.domain.usecase.permissao.buscarPermissoesUsuario.BuscarPermissoesUsuarioUsecase;
import br.com.molens.odontoDelta.domain.usecase.permissao.validarPermissoesUsuario.ValidarPermissaoUsuarioInput;
import br.com.molens.odontoDelta.gateway.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UserDataUsecase {

    private UsuarioDataProvider usuarioDataProvider;
    private SessaoUsuarioDataProvider sessaoUsuarioDataProvider;
    private BuscarPermissoesUsuarioUsecase buscarPermissoesUsuarioUsecase;

    public ResponseUserBody executar(){
        SessaoUsuario sessaoUsuario = buscarSessaoUsuario();
        Usuario usuario = buscarUsuario(sessaoUsuario);
        BuscarPermissoesUsuarioOutput permissoes = buscarPermissoesUsuario(usuario);
        return setarDados(usuario, permissoes);
    }

    private SessaoUsuario buscarSessaoUsuario() {
        return sessaoUsuarioDataProvider.get();
    }

    private Usuario buscarUsuario(SessaoUsuario sessaoUsuario) {
        Optional<Usuario> usuario = usuarioDataProvider.buscarPorId(sessaoUsuario.getId());
        if(!usuario.isPresent())
            throw new SessaoUsuarioException("Falha ao obter informações do usuário!");

        return usuario.get();
    }

    private BuscarPermissoesUsuarioOutput buscarPermissoesUsuario(Usuario usuario) {
        return buscarPermissoesUsuarioUsecase.executar(BuscarPermissoesUsuarioInput.builder()
                .usuarioId(usuario.getId())
                .empresaId(usuario.getEmpresa().getId())
                .build());
    }

    private ResponseUserBody setarDados(Usuario usuario, BuscarPermissoesUsuarioOutput permissoes) {

        return ResponseUserBody.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .nomeCompleto(usuario.getNome())
                .empresa(ResponseUserBody.Empresa.builder()
                        .id(usuario.getEmpresa().getId())
                        .nome(usuario.getEmpresa().getNome())
                        .razaoSocial(usuario.getEmpresa().getRazaoSocial())
                        .build())
                .permissao(setarPermissoes(permissoes))
                .build();
    }

    private List<String> setarPermissoes(BuscarPermissoesUsuarioOutput permissoes) {
        List<String> permissoesList = new ArrayList<>();

        if(!permissoes.getItems().isEmpty()){
            for(BuscarPermissoesUsuarioOutput.Permissao permissao : permissoes.getItems()){
                permissoesList.add(permissao.getNome());
            }
        }

        return permissoesList;
    }

}
