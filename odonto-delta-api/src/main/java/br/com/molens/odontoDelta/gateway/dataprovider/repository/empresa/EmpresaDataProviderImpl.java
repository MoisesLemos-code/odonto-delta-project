package br.com.molens.odontoDelta.gateway.dataprovider.repository.empresa;

import br.com.molens.odontoDelta.domain.entity.Empresa;
import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.gateway.dataprovider.converter.EmpresaConverter;
import br.com.molens.odontoDelta.gateway.dataprovider.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpresaDataProviderImpl implements EmpresaDataProvider {

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private EmpresaConverter converter;

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return repository.findById(id).map(converter::to);
    }

    @Override
    public void inserir(Empresa empresa) {
        repository.save(converter.from(empresa));
    }

    @Override
    public Empresa atualizar(Empresa empresa) {
        EmpresaEntity empresaEntity = repository.save(converter.from(empresa));
        return converter.to(empresaEntity);
    }
}
