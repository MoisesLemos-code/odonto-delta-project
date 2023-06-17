package br.com.molens.odontoDelta.utils;

import br.com.molens.odontoDelta.domain.entity.FiltroBase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class ConstrutorPaginacao {

    private ConstrutorPaginacao() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable extrairPaginacao(FiltroBase filtro) {

        if (filtro.getPage() > 0) {
            filtro.setPage(filtro.getPage() - 1);
        }else {
            filtro.setPage(0);
        }

        return PageRequest.of(
                filtro.getPage(), (filtro.getSize() == 0 ? 10 : filtro.getSize()),
                Sort.Direction.valueOf(Objects.nonNull(filtro.getDirection()) ? filtro.getDirection() : "ASC"),
                Objects.nonNull(filtro.getSort()) ? filtro.getSort() : "id"
        );
    }

}
