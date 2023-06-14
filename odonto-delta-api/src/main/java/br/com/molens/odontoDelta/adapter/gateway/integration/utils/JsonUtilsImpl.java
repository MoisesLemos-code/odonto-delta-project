package br.com.molens.odontoDelta.adapter.gateway.integration.utils;

import br.com.molens.odontoDelta.utils.IJsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonUtilsImpl implements IJsonUtils {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'").create();

    @Override
    public String toJson(Object src) {
        return gson.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
