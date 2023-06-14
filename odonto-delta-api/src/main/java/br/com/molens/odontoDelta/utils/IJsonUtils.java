package br.com.molens.odontoDelta.utils;

public interface IJsonUtils {

    String toJson(Object src);

    <T> T fromJson(String json, Class<T> classOfT);
}
