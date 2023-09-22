package br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.interfaces.PerfilDataProvider;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.converter.BuscaPaginadaPerfilFiltroConverter;
import br.com.molens.odontoDelta.domain.usecase.perfil.buscaPaginada.converter.BuscaPaginadaPerfilOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BuscaPaginadaPerfilUsecase {

    private PerfilDataProvider perfilDataProvider;
    private BuscaPaginadaPerfilFiltroConverter filtroConverter;
    private BuscaPaginadaPerfilOutputConverter outputConverter;

    public BuscaPaginadaPerfilOutput executar(BuscaPaginadaPerfilInput input){
        Perfil.Filtro filtro = criarFiltro(input);
        ListaPaginada<Perfil> entidadesEncontradas = buscar(filtro);

        return outputConverter.to(entidadesEncontradas);
    }

    private Perfil.Filtro criarFiltro(BuscaPaginadaPerfilInput input) {
        return this.filtroConverter.to(input);
    }

    private ListaPaginada<Perfil> buscar(Perfil.Filtro filtro) {
        return perfilDataProvider.buscaPaginadaComFiltro(filtro);
    }
}
