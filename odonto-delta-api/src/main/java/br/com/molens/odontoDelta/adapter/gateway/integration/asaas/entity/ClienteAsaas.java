package br.com.molens.odontoDelta.adapter.gateway.integration.asaas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAsaas {

    private String id;
    private String address;
    private String addressNumber;
    private String city;
    private String complement;
    private String cpfCnpj;
    private String email;
    private String mobilePhone;
    private String name;
    private String phone;
    private String postalCode;
    private String province;
    private String state;

}