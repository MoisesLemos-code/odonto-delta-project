package br.com.molens.odontoDelta.gateway.dataprovider.repository.municipio;

import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.entity.Municipio;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.domain.interfaces.MunicipioDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.EmpresaConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.MunicipioConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import br.com.molens.odontoDelta.gateway.dataprovider.repository.empresa.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MunicipioDataProviderImpl implements MunicipioDataProvider {

    @Autowired
    private MunicipioRepository repository;

    @Autowired
    private MunicipioConverter converter;

    @Override
    public Optional<Municipio> buscarPorId(Long id) {
        return repository.findById(id).map(converter::to);
    }
}
