package br.com.molens.odontoDelta.gateway.repository.empresa;

import br.com.molens.odontoDelta.domain.interfaces.EmpresaDataProvider;
import br.com.molens.odontoDelta.gateway.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmpresaDataProviderImpl implements EmpresaDataProvider {

    @Autowired
    private EmpresaRepository repository;

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void inserir(Empresa empresa) {
        repository.save(empresa);
    }

    @Override
    public Empresa atualizar(Empresa empresa) {
        return repository.save(empresa);
    }
}
