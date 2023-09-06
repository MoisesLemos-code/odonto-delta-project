package br.com.molens.odontoDelta.domain.usecase.sessaoUsuario.userData;

import br.com.molens.odontoDelta.application.security.ResponseUserBody;
import br.com.molens.odontoDelta.domain.entity.SessaoUsuario;
import br.com.molens.odontoDelta.domain.exception.SessaoUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.SessaoUsuarioDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.UsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
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

    public ResponseUserBody executar(){
        SessaoUsuario sessaoUsuario = buscarSessaoUsuario();
        Usuario usuario = buscarUsuario(sessaoUsuario);
        return setarDados(usuario);
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

    private ResponseUserBody setarDados(Usuario usuario) {


        return ResponseUserBody.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .nomeCompleto(usuario.getNome())
                .empresa(ResponseUserBody.Empresa.builder()
                        .id(usuario.getEmpresa().getId())
                        .nome(usuario.getEmpresa().getNome())
                        .razaoSocial(usuario.getEmpresa().getRazaoSocial())
                        .build())
                .permissao(setarPermissoes(usuario))
                .build();
    }

    private List<String> setarPermissoes(Usuario usuario) {
        List<String> permissoes = new ArrayList<>();
        if(usuario.getLogin().equals("admin")){
            permissoes.add("ADMINISTRADOR");
        }
        return permissoes;
    }

}
