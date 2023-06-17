package br.com.molens.odontoDelta.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FiltroBase {
    int page;
    int size;
    String sort;
    String direction;
}
