package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.notaFiscal;

import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.NotaFiscalAsaas;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.RestUtils;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception.AsaasValidationException;
import br.com.molens.odontoDelta.domain.interfaces.integration.NotaFiscalAsaasDataProvider;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class NotaFiscalAsaasDataProviderImpl implements NotaFiscalAsaasDataProvider {

    @Autowired
    RestUtils restUtils;

    @Override
    public NotaFiscalAsaas criarNotaFiscal(String url, String token, NotaFiscalAsaas notaFiscal) {
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), gson.toJson(notaFiscal));

        try {
            Response response = restUtils.post(url, token, body);
            RestUtils.handleStatusError(response, "Problemas na execução da requisição de agendamento de nota fiscal.");

            return extrairResposta(response);
        } catch (IOException e) {
            String mensagem = "Falha ao tentar consumir o serviço de agendamento de nota fiscal.";
            Logger.getGlobal().log(Level.SEVERE, String.format("%s (url: %s)", mensagem, url), e);

            throw new AsaasValidationException(mensagem);
        }
    }

    private NotaFiscalAsaas extrairResposta(Response response) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(Objects.requireNonNull(response.body()).string(), NotaFiscalAsaas.class);
    }
}
