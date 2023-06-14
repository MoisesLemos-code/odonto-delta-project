package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.cliente;

import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.ClienteAsaas;
import br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity.ListaAsaasResponse;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.RestUtils;
import br.com.molens.odontoDelta.adapter.gateway.integration.utils.exception.AsaasValidationException;
import br.com.molens.odontoDelta.domain.interfaces.integration.ClienteAsaasDataProvider;
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
public class ClienteAsaasDataProviderImpl implements ClienteAsaasDataProvider {

    @Autowired
    RestUtils restUtils;

    @Override
    public ClienteAsaas criarNovoCliente(String url, String token, ClienteAsaas cliente) {
        try {
            Gson gson = new Gson();
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(mediaType, gson.toJson(cliente));

            Response response = restUtils.post(url, token, body);

            RestUtils.handleStatusError(response, "Problemas na execução da requisição do cadastro de cliente.");

            return this.extrairResposta(response);
        } catch (IOException e) {
            String mensagem = "Falha ao tentar consumir o serviço que cadastra o cliente.";
            Logger.getGlobal().log(Level.SEVERE, String.format("%s (url: %s)", mensagem, url), e);

            throw new AsaasValidationException(mensagem);
        }
    }

    @Override
    public ListaAsaasResponse buscarClientePorDocumento(String url, String token) {
        try {
            Response response = restUtils.get(url, token);

            RestUtils.handleStatusError(response, "Problemas na execução da requisição de busca de cliente por documento.");

            return this.extrairListaResposta(response);
        } catch (IOException e) {
            String mensagem = "Falha ao tentar consumir o serviço que busca o cliente por documento.";
            Logger.getGlobal().log(Level.SEVERE, String.format("%s (url: %s)", mensagem, url), e);

            throw new AsaasValidationException(mensagem);
        }
    }

    private ClienteAsaas extrairResposta(Response response) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(Objects.requireNonNull(response.body()).string(), ClienteAsaas.class);
    }

    private ListaAsaasResponse extrairListaResposta(Response response) throws IOException {
        Gson gson = new Gson();

        return gson.fromJson(Objects.requireNonNull(response.body()).string(), ListaAsaasResponse.class);
    }
}
