package br.com.molens.odontoDelta.domain.interfaces.integration;

import br.com.molens.odontoDelta.domain.exception.FalhaPublicacaoMensagemException;

import java.util.Map;

public interface MensageriaDataProvider {

    void declararFila(String destino, String nomeFila, String chaveRoteamento, Map<String, Object> propriedadesFila);

    Boolean verificarSeFilaExiste(String nomeFila);

    void publicarMensagem(String destino, String chaveRoteamento, Object mensagem, Map<String, Object> propriedadesMensagem) throws FalhaPublicacaoMensagemException;
}
