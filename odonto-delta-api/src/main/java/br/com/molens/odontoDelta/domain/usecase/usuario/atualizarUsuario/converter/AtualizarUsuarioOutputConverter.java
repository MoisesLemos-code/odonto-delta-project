package br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.converter;

import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioInput;
import br.com.molens.odontoDelta.domain.usecase.usuario.atualizarUsuario.AtualizarUsuarioOutput;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Empresa;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Municipio;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Perfil;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.Usuario;
import br.com.molens.odontoDelta.utils.converter.GenericConverter;

import java.util.Objects;

public class AtualizarUsuarioOutputConverter extends GenericConverter<Usuario, AtualizarUsuarioOutput> {

    @Override
    public AtualizarUsuarioOutput to(Usuario source) {
        AtualizarUsuarioOutput target = super.to(source);

        if (Objects.nonNull(source.getEmpresa())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresa().getId())
                    .build());
        }

        if (Objects.nonNull(source.getPerfil())) {
            target.setPerfil(Perfil.builder()
                    .id(source.getPerfil().getId())
                    .build());
        }

        if (Objects.nonNull(source.getMunicipio())) {
            target.setMunicipio(Municipio.builder()
                    .id(source.getMunicipio().getId())
                    .build());
        }


        return target;
    }

    public Usuario from(AtualizarUsuarioInput source) {
        Usuario target = new Usuario();

        target.setId(source.getId());
        target.setLogin(source.getLogin());
        target.setSenha(source.getSenha());
        target.setNome(source.getNome());
        target.setEmail(source.getEmail());
        target.setCnpjCpf(source.getCnpjCpf());
        target.setTelefone(source.getTelefone());
        target.setCep(source.getCep());
        target.setBairro(source.getBairro());
        target.setLogradouro(source.getLogradouro());
        target.setLogradouroNumero(source.getLogradouroNumero());
        target.setComplemento(source.getComplemento());
        target.setSituacao(source.getSituacao());
        target.setCargo(source.getCargo());
        target.setDepartamento(source.getDepartamento());

        if (Objects.nonNull(source.getEmpresaId())) {
            target.setEmpresa(Empresa.builder()
                    .id(source.getEmpresaId())
                    .build());
        }

        if (Objects.nonNull(source.getMunicipioId())) {
            target.setMunicipio(Municipio.builder()
                    .id(source.getMunicipioId())
                    .build());
        }

        if (Objects.nonNull(source.getPerfilId())) {
            target.setPerfil(Perfil.builder()
                    .id(source.getPerfilId())
                    .build());
        }

        return target;
    }
}