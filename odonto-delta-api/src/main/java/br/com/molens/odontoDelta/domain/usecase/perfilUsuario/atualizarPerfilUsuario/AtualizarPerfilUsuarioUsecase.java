package br.com.molens.odontoDelta.domain.usecase.perfilUsuario.atualizarPerfilUsuario;

import br.com.molens.odontoDelta.domain.exception.AtualizarPerfilUsuarioException;
import br.com.molens.odontoDelta.domain.interfaces.PerfilUsuarioDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import br.com.molens.odontoDelta.gateway.entity.PerfilUsuario;
import br.com.molens.odontoDelta.gateway.entity.PerfilUsuarioPK;
import br.com.molens.odontoDelta.gateway.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
public class AtualizarPerfilUsuarioUsecase {

    private PerfilUsuarioDataProvider perfilUsuarioDataProvider;

    public void executar(AtualizarPerfilUsuarioInput input){
        validarDadosEntrada(input);
        atualizarPerfilPermissao(input);
    }

    private void validarDadosEntrada(AtualizarPerfilUsuarioInput input) {
        if (Objects.isNull(input.getUsuarioId())) {
            throw new AtualizarPerfilUsuarioException("Identificador de usuário inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new AtualizarPerfilUsuarioException("Identificador de empresa inválido.");
        }
    }

    private void atualizarPerfilPermissao(AtualizarPerfilUsuarioInput input) {

        if(!input.getItems().isEmpty()){

            List<AtualizarPerfilUsuarioInput.Perfil> perfilsAtivos = input.getItems().stream().filter(AtualizarPerfilUsuarioInput.Perfil::getAtivo).collect(Collectors.toList());
            inserirPerfisAtivos(perfilsAtivos, input.getUsuarioId());

            List<AtualizarPerfilUsuarioInput.Perfil> perfilsNaoAtivos = input.getItems().stream().filter(item -> !item.getAtivo()).collect(Collectors.toList());
            removerPerfisNaoAtivo(perfilsNaoAtivos, input.getUsuarioId());
        }
    }

    private void inserirPerfisAtivos(List<AtualizarPerfilUsuarioInput.Perfil> perfilsAtivos, Long usuarioId) {
        List<PerfilUsuario> perfilPermissaoListAtivos = new ArrayList<>();

        for(AtualizarPerfilUsuarioInput.Perfil item : perfilsAtivos){
            PerfilUsuario perfilUsuario = new PerfilUsuario();

            perfilUsuario.setPerfil(Perfil.builder().id(item.getIdPerfil()).build());
            perfilUsuario.setUsuario(Usuario.builder().id(usuarioId).build());
            perfilUsuario.setPerfilUsuarioPK(PerfilUsuarioPK.builder()
                            .idPerfil(item.getIdPerfil())
                            .idUsuario(usuarioId)
                    .build());

            perfilPermissaoListAtivos.add(perfilUsuario);
        }

        perfilUsuarioDataProvider.inserirTodos(perfilPermissaoListAtivos);
    }

    private void removerPerfisNaoAtivo(List<AtualizarPerfilUsuarioInput.Perfil> perfilsNaoAtivos, Long usuarioId) {

        List<PerfilUsuario> perfilPermissaoListNaoAtivos = new ArrayList<>();

        for(AtualizarPerfilUsuarioInput.Perfil item : perfilsNaoAtivos){
            PerfilUsuario perfilUsuario = new PerfilUsuario();

            perfilUsuario.setPerfil(Perfil.builder().id(item.getIdPerfil()).build());
            perfilUsuario.setUsuario(Usuario.builder().id(usuarioId).build());
            perfilUsuario.setPerfilUsuarioPK(PerfilUsuarioPK.builder()
                    .idPerfil(item.getIdPerfil())
                    .idUsuario(usuarioId)
                    .build());

            perfilPermissaoListNaoAtivos.add(perfilUsuario);
        }

        perfilUsuarioDataProvider.removerTodos(perfilPermissaoListNaoAtivos);
    }

}
