package br.com.molens.odontoDelta.domain.enumeration;

public enum SituacaoPacienteEnum {

    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String valorAtributo;

    SituacaoPacienteEnum(String valor) {
        valorAtributo = valor;
    }

    public String getValorAtributo() {
        return valorAtributo;
    }

    public static String getFrom(String valorEnum){
        for(SituacaoPacienteEnum e : SituacaoPacienteEnum.values()){
            if(e.valorAtributo.equals(valorEnum)) return e.name();
        }
        return null;
    }
}
