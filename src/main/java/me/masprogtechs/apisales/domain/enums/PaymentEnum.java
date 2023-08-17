package me.masprogtechs.apisales.domain.enums;

public enum PaymentEnum {
    DINHEIRO("DINHEIRO"),
    CARTAO("CARTÃO");

    private String name;

    public String getDescricao(){
        return name;
    }

    private PaymentEnum(String name){
        this.name = name;
    }
}
