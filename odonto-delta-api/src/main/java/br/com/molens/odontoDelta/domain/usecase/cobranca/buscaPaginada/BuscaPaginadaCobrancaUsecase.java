package br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada;

import br.com.molens.odontoDelta.domain.entity.ListaPaginada;
import br.com.molens.odontoDelta.domain.exception.BuscaPaginadaCobrancaException;
import br.com.molens.odontoDelta.domain.interfaces.CobrancaDataProvider;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter.BuscaPaginadaCobrancaFiltroConverter;
import br.com.molens.odontoDelta.domain.usecase.cobranca.buscaPaginada.converter.BuscaPaginadaCobrancaOutputConverter;
import br.com.molens.odontoDelta.gateway.entity.Cobranca;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class BuscaPaginadaCobrancaUsecase {

    private CobrancaDataProvider cobrancaDataProvider;
    private BuscaPaginadaCobrancaFiltroConverter filtroConverter;
    private BuscaPaginadaCobrancaOutputConverter outputConverter;

    public BuscaPaginadaCobrancaOutput executar(BuscaPaginadaCobrancaInput input){
        validarDadosEntrada(input);
        Cobranca.Filtro filtro = criarFiltro(input);
        ListaPaginada<Cobranca> entidadesEncontradas = buscar(filtro);

        return outputConverter.to(entidadesEncontradas);
    }

    private void validarDadosEntrada(BuscaPaginadaCobrancaInput input) {
        if (Objects.isNull(input.getPacienteId())) {
            throw new BuscaPaginadaCobrancaException("Identificador de paciente inválido.");
        }
        if (Objects.isNull(input.getEmpresaId())) {
            throw new BuscaPaginadaCobrancaException("Identificador de empresa inválido.");
        }
    }

    private Cobranca.Filtro criarFiltro(BuscaPaginadaCobrancaInput input) {
        return this.filtroConverter.to(input);
    }

    private ListaPaginada<Cobranca> buscar(Cobranca.Filtro filtro) {
        return cobrancaDataProvider.buscaPaginadaComFiltro(filtro);
    }

}
