package br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil;

import br.com.molens.odontoDelta.domain.exception.InserirPerfilException;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.inserirPerfil.converter.InserirPerfilOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class InserirPerfilUsecase {

    private PerfilDataProvider perfilDataProvider;
    private EmpresaDataProvider empresaDataProvider;
    private InserirPerfilOutputConverter outputConverter;

    public InserirPerfilOutput executar(InserirPerfilInput input) {
        validarDadosEntrada(input);
        validarEmpresa(input);
        validarPerfilJaCadastrado(input);
        return inserirPerfil(input);
    }

    private void validarDadosEntrada(InserirPerfilInput input) {

        if (Objects.isNull(input.getEmpresaId())) {
            throw new InserirPerfilException("Identificador de empresa inválido.");
        }
    }

    private void validarEmpresa(InserirPerfilInput input) {
        Optional<Empresa> empresa = empresaDataProvider.buscarPorId(input.getEmpresaId());
        if (!empresa.isPresent()) {
            throw new InserirPerfilException("Empresa não identificada.");
        }
    }

    private void validarPerfilJaCadastrado(InserirPerfilInput input) {
        if (perfilDataProvider.existeNome(input.getNome(), input.getEmpresaId())) {
            throw new InserirPerfilException("Já existe um perfil com esse nome!");
        }
    }

    private InserirPerfilOutput inserirPerfil(InserirPerfilInput input) {
        input.setAtivo(Boolean.TRUE);
        Long perfilId = perfilDataProvider.inserir(outputConverter.from(input));

        return new InserirPerfilOutput(perfilId);
    }

}
